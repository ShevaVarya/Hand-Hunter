package ru.practicum.android.diploma.features.filters.domain.api.workplace

import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData

interface SelectWorkplaceInteractor {
    fun getFullLocationData(): FullLocationData

    fun deleteCountryData()
    fun deleteRegionData()
}
