package ru.practicum.android.diploma.features.filters.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Region

interface LocationManager {
    fun subscribeLocationData(): Flow<FullLocationData?>

    fun keepRegion(country: Country, region: Region)
    fun keepCountry(country: Country)

    fun getCountryId(): String?

    fun deleteCountry()
    fun deleteRegion()

    fun acceptData()
}

class LocationManagerImpl(
    private val filterRepository: FilterRepository,
    private val filterManager: FilterManager
) : LocationManager {
    private var data: FullLocationData = getLocationData() ?: FullLocationData(
        country = null,
        region = null
    )

    override fun subscribeLocationData(): Flow<FullLocationData?> {
        return flowOf(data)
    }

    override fun keepRegion(country: Country, region: Region) {
        data = data.copy(country = country, region = region)
    }

    override fun keepCountry(country: Country) {
        data = data.copy(country = country)
        data.region?.let {
            data = data.copy(region = null)
        }
    }

    override fun getCountryId(): String? {
        return data.country?.id
    }

    override fun deleteCountry() {
        data = data.copy(country = null, region = null)
    }

    override fun deleteRegion() {
        data = data.copy(region = null)
    }

    override fun acceptData() {
        filterManager.keepWorkplace(data)
    }

    private fun getLocationData(): FullLocationData? {
        return filterRepository.getFullLocationData()
    }
}
