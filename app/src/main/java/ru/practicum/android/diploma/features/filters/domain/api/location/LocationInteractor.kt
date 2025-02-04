package ru.practicum.android.diploma.features.filters.domain.api.location

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterRegion
import ru.practicum.android.diploma.features.filters.domain.model.Region

interface LocationInteractor {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>>
    suspend fun getAllAreasByIdList(
        countryId: String,
        params: Map<String, String>
    ): Result<List<Region>>
    suspend fun getOriginalAreasList(params: Map<String, String>):Result<List<Region>>

    fun setCountry(country: Country)
    fun setRegion(region: FilterRegion)
    fun getCountryId(): String
}
