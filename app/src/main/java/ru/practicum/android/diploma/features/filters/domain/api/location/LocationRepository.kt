package ru.practicum.android.diploma.features.filters.domain.api.location

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Region

interface LocationRepository {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>>
    suspend fun getAllAreasByIdList(
        countryId: String,
        params: Map<String, String>
    ): Result<List<Region>>
}
