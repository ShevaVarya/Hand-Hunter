package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.api.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.workplace.SelectWorkplaceInteractor

class SelectWorkplaceInteractorImpl(
    private val filterRepository: FilterRepository
) : SelectWorkplaceInteractor {
    override fun getFullLocationData(): FullLocationData {
        return filterRepository.getFullLocationData()
    }

    override fun deleteCountryData() {
        filterRepository.deleteCountryData()
    }

    override fun deleteRegionData() {
        filterRepository.deleteRegionData()
    }
}
