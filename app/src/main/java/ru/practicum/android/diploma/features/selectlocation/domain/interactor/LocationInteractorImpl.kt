package ru.practicum.android.diploma.features.selectlocation.domain.interactor

import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationInteractor
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationRepository
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

class LocationInteractorImpl(
    private val locationRepository: LocationRepository
) : LocationInteractor {
    override suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>> {
        return locationRepository.getCountriesList(params)
    }

    override suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>> {
        return locationRepository.getAllAreasList(params)
    }

    override fun setCountry(country: Country) {
        locationRepository.setCountry(country)
    }

    override fun setRegion(region: Region) {
        locationRepository.setRegion(region)
    }
}
