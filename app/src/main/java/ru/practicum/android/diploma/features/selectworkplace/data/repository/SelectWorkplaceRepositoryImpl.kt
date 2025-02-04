package ru.practicum.android.diploma.features.selectworkplace.data.repository

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.toDomain
import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData
import ru.practicum.android.diploma.features.selectworkplace.domain.api.SelectWorkplaceRepository

class SelectWorkplaceRepositoryImpl(
    private val filterStorage: FilterStorage
) : SelectWorkplaceRepository {
    override fun getFullLocationData(): FullLocationData {
        return filterStorage.getFullLocationData().toDomain()
    }

    override fun deleteCountryData() {
        filterStorage.deleteCountryData()
    }

    override fun deleteRegionData() {
        filterStorage.deleteRegionData()
    }
}
