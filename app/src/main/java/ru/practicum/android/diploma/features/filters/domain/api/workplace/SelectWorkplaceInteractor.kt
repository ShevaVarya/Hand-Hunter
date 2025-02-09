package ru.practicum.android.diploma.features.filters.domain.api.workplace

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Region

interface SelectWorkplaceInteractor {
    fun getFullLocationData(): FullLocationData?

    fun deleteCountryData()
    fun deleteRegionData()

    fun setCountry(country: Country)
    fun setRegion(region: Region)
}
