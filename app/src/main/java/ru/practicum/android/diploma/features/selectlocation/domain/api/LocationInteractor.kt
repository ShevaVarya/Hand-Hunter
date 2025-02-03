package ru.practicum.android.diploma.features.selectlocation.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

interface LocationInteractor {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>>
    suspend fun getAllAreasByIdList(
        countryId: String,
        params: Map<String, String>
    ): Result<List<Region>>

    fun setCountry(country: FilterCountry)
    fun setRegion(region: FilterRegion)
    fun getCountryId(): String
}
