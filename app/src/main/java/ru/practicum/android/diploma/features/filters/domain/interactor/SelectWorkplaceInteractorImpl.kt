package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.workplace.SelectWorkplaceInteractor
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterRegion
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData

class SelectWorkplaceInteractorImpl(
    private val filterRepository: FilterRepository
) : SelectWorkplaceInteractor {
    override fun getFullLocationData(): FullLocationData? {
        return filterRepository.getFullLocationData()
    }

    override fun deleteCountryData() {
        filterRepository.deleteCountryData()
    }

    override fun deleteRegionData() {
        filterRepository.deleteRegionData()
    }

    override fun setCountry(country: Country) {
        filterRepository.setCountry(country)
    }

    override fun setRegion(region: FilterRegion) {
        filterRepository.setRegion(region)
    }
}
