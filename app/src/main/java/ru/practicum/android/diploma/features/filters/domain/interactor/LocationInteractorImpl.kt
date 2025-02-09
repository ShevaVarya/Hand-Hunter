package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationInteractor
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationRepository
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Region

class LocationInteractorImpl(
    private val locationRepository: LocationRepository,
    private val filterRepository: FilterRepository
) : LocationInteractor {
    override suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>> {
        return locationRepository.getCountriesList(params)
    }

    override suspend fun getOriginalAreasList(params: Map<String, String>): Result<List<Region>> {
        return locationRepository.getAllAreasList(params)
    }

    override fun getSortedFilteredRegionsList(
        list: List<Region>,
        newList: MutableList<Region>,
        countryId: String?
    ): List<Region> {
        var listWithoutOtherRegions = list.filter { it.id != OTHER_COUNTRIES_ID }

        countryId?.let { listWithoutOtherRegions = listWithoutOtherRegions.filter { it.id == countryId } }

        return filterRegionsList(listWithoutOtherRegions, newList).sortedBy { it.name }
    }

    private fun filterRegionsList(
        list: List<Region>,
        newList: MutableList<Region>
    ): List<Region> {
        list.forEach {
            if (it.areas.isNullOrEmpty()) {
                newList.add(it)
            } else {
                filterRegionsList(it.areas, newList)
            }
        }
        return newList
    }

    override fun setCountry(country: Country) {
        filterRepository.setCountry(country)
    }

    override fun setRegion(region: Region) {
        filterRepository.setRegion(region)
    }

    override fun getCountryId(): String? {
        return filterRepository.getCountryId()
    }

    override fun deleteRegionWhenChangeCountry() {
        filterRepository.deleteRegionData()
    }

    companion object {
        private const val OTHER_COUNTRIES_ID = "1001"
    }
}
