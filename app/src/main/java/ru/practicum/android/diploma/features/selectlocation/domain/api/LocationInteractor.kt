package ru.practicum.android.diploma.features.selectlocation.domain.api

import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

interface LocationInteractor {
    suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>>
    fun setCountry(country: Country)
}
