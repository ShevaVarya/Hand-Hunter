package ru.practicum.android.diploma.features.selectworkplace.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

interface SelectWorkplaceInteractor {
    fun getFullLocationData(): FullLocationData

    fun deleteCountryData()
    fun deleteRegionData()
}
