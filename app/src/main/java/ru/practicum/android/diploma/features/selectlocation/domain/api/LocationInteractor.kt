package ru.practicum.android.diploma.features.selectlocation.domain.api

import ru.practicum.android.diploma.features.selectlocation.domain.model.Region
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

interface LocationInteractor {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>>
    fun setCountry(country: Country)
    fun setRegion(region: Region)
}
