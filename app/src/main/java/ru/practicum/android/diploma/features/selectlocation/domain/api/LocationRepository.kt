package ru.practicum.android.diploma.features.selectlocation.domain.api

import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

interface LocationRepository {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    fun setCountry(country: Country)
}
