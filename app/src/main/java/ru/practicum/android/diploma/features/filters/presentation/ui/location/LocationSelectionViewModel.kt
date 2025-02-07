package ru.practicum.android.diploma.features.filters.presentation.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationInteractor
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Region
import ru.practicum.android.diploma.features.filters.presentation.model.api.Regionable
import ru.practicum.android.diploma.features.filters.presentation.model.state.LocationSelectionState
import ru.practicum.android.diploma.features.filters.presentation.model.toUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.RegionUI
import kotlin.coroutines.cancellation.CancellationException

class LocationSelectionViewModel(
    private val isCountry: Boolean,
    private val locationInteractor: LocationInteractor
) : ViewModel() {
    private var countryId: String = ""
    private var regionList: List<RegionUI> = listOf()
    private var areasDomainList: List<Region> = listOf()
    private var countriesDomainList: List<Country> = listOf()

    private var _state = MutableStateFlow<LocationSelectionState>(LocationSelectionState.Loading)
    val state = _state.asStateFlow()

    init {
        if (isCountry.not()) {
            countryId = locationInteractor.getCountryId() ?: ""
        }
        getData()
    }

    fun search(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredList = filterListByText(text.trim())
            withContext(Dispatchers.Main) {
                if (filteredList.isEmpty()) {
                    _state.value = LocationSelectionState.NoRegionError
                } else {
                    _state.value = LocationSelectionState.ContentRegion(filteredList)
                }
            }
        }
    }

    private fun getData() {
        if (isCountry) {
            getCountryList()
        } else {
            getAreasList()
        }
    }

    private fun getCountryList() {
        viewModelScope.launch {
            locationInteractor.getCountriesList(mapOf())
                .onSuccess { list ->
                    countriesDomainList = list
                    _state.value = LocationSelectionState.ContentCountry(list.map { it.toUI() })
                }
                .onFailure { handleError(it) }
        }
    }

    private fun getAreasList() {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf()
            val result = locationInteractor.getOriginalAreasList(params)
            result
                .onSuccess {
                    areasDomainList = result.getOrNull() ?: listOf()
                    if (areasDomainList.isEmpty().not()) {
                        regionList = locationInteractor.getSortedFilteredRegionsList(
                            areasDomainList,
                            mutableListOf(),
                            countryId.ifEmpty { null }
                        )
                            .filter { it.parentId.isNullOrEmpty().not() }
                            .map { it.toUI() }

                        _state.value = LocationSelectionState.ContentRegion(regionList)
                    }
                }
                .onFailure { handleError(it) }
        }
    }

    fun saveRegion(region: Regionable) {
        if (isCountry) {
            val country = countriesDomainList.firstOrNull { it.id == region.id }
            country?.let {
                locationInteractor.setCountry(country)
                locationInteractor.deleteRegionWhenChangeCountry()
            }
        } else {
            region.id?.let { id ->
                val area: Region? = findRegionById(areasDomainList, id)

                area?.let {
                    locationInteractor.setRegion(area)
                    saveRegionCountry(area)
                }
            }
        }
    }

    private fun saveRegionCountry(area: Region) {
        area.id?.let {
            val country = areasDomainList.firstOrNull { isParentFind(it, area.id) } ?: return
            locationInteractor.setCountry(Country(id = country.id, name = country.name))
        }
    }

    private fun isParentFind(region: Region, areaId: String): Boolean {
        if (areaId == region.id) {
            return true
        }

        return region.areas?.any { isParentFind(it, areaId) } ?: false
    }

    private fun findRegionById(areas: List<Region>, areaId: String): Region? {
        areas.forEach { area ->
            if (area.id == areaId) return area
            area.areas?.let {
                val result = findRegionById(it, areaId)
                if (result != null) {
                    return result
                }
            }
        }
        return null
    }

    private fun handleError(error: Throwable) {
        when (error) {
            is CustomException.RequestError, CustomException.NetworkError, CustomException.ServerError -> {
                _state.value = LocationSelectionState.NetworkError
            }

            is CancellationException -> throw error
            else -> Unit
        }
    }

    private fun filterListByText(text: String): List<RegionUI> {
        return if (text.isEmpty()) {
            regionList
        } else {
            regionList.filter { item ->
                item.name?.contains(text, ignoreCase = true) ?: false
            }
        }
    }
}
