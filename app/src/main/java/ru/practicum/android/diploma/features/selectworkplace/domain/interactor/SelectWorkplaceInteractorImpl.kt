package ru.practicum.android.diploma.features.selectworkplace.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData
import ru.practicum.android.diploma.features.selectworkplace.domain.api.SelectWorkplaceInteractor
import ru.practicum.android.diploma.features.selectworkplace.domain.api.SelectWorkplaceRepository

class SelectWorkplaceInteractorImpl(
    private val selectWorkplaceRepository: SelectWorkplaceRepository
) : SelectWorkplaceInteractor {
    override fun getFullLocationData(): FullLocationData {
        return selectWorkplaceRepository.getFullLocationData()
    }

    override fun deleteCountryData() {
        selectWorkplaceRepository.deleteCountryData()
    }

    override fun deleteRegionData() {
        selectWorkplaceRepository.deleteRegionData()
    }
}
