package ru.practicum.android.diploma.features.selectlocation.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationInteractor
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationRepository
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

class LocationInteractorImpl(
    private val locationRepository: LocationRepository,
    private val filterRepository: FilterRepository
) : LocationInteractor {
    override suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>> {
        return locationRepository.getCountriesList(params)
    }

    override suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>> {
        return locationRepository.getAllAreasList(params).map { list ->
            getSortedFilteredRegionsList(list, mutableListOf())
        }
    }

    override suspend fun getAllAreasByIdList(countryId: String, params: Map<String, String>): Result<List<Region>> {
        return locationRepository.getAllAreasByIdList(countryId, params).map { list ->
            getSortedFilteredRegionsList(list, mutableListOf())
        }
    }

    override fun setCountry(country: FilterCountry) {
        filterRepository.setCountry(country)
    }

    override fun setRegion(region: FilterRegion) {
        filterRepository.setRegion(region)
    }

    override fun getCountryId(): String {
        return filterRepository.getCountryId()
    }

    private fun getSortedFilteredRegionsList(list: List<Region>, newList: MutableList<Region>): List<Region> {
        list.forEach {
            if (it.areas.isEmpty()) {
                newList.add(it)
            } else {
                getSortedFilteredRegionsList(it.areas, newList)
            }
        }
        return newList.sortedBy { it.name }
    }
}
