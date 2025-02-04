package ru.practicum.android.diploma.features.selectworkplace.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.selectworkplace.domain.api.SelectWorkplaceInteractor

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
