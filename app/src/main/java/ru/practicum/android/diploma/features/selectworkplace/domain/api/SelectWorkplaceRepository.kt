package ru.practicum.android.diploma.features.selectworkplace.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

interface SelectWorkplaceRepository {
    fun getFullLocationData(): FullLocationData

    fun deleteCountryData()
    fun deleteRegionData()
}
