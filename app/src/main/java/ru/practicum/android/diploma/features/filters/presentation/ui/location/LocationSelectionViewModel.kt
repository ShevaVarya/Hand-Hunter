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
import ru.practicum.android.diploma.features.filters.presentation.model.toDomain
import ru.practicum.android.diploma.features.filters.presentation.model.toUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.CountryUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.RegionUI
import kotlin.coroutines.cancellation.CancellationException

class LocationSelectionViewModel(
    private val isCountry: Boolean,
    private val locationInteractor: LocationInteractor
) : ViewModel() {
    private var countryId: String = ""
    private var regionList: List<RegionUI> = listOf()
    private var originalAreasList: List<Region> = listOf()

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

    fun saveRegion(region: Regionable) {
        if (isCountry) {
            locationInteractor.setCountry((region as CountryUI).toDomain())
            locationInteractor.deleteRegionWhenChangeCountry()
        } else {
            locationInteractor.setRegion((region as RegionUI).toDomain())
            saveRegionCountry(region)
        }
    }

    private fun getData() {
        if (isCountry) {
            getCountryList()
        } else {
            getRegionList()
            getOriginalAreasList()
        }
    }

    private fun getCountryList() {
        viewModelScope.launch {
            locationInteractor.getCountriesList(mapOf())
                .onSuccess { list ->
                    _state.value = LocationSelectionState.ContentCountry(list.map { it.toUI() })
                }
                .onFailure {
                    handleError(it)
                }
        }
    }

    private fun saveRegionCountry(item: RegionUI) {
        item.id?.let {
            val country = originalAreasList.firstOrNull { isParentFind(it, item.id) }?.toUI() ?: return
            locationInteractor.setCountry(Country(id = country.id, name = country.name))
        }

    }

    private fun isParentFind(region: Region, cityId: String): Boolean {
        if (cityId == region.id) {
            return true
        }

        return region.areas.any { isParentFind(it, cityId) }
    }

    private fun getRegionList() {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf()

            val result = if (countryId.isEmpty()) {
                locationInteractor.getAllAreasList(params)
            } else {
                locationInteractor.getAllAreasByIdList(countryId, params)
            }

            result
                .onSuccess { list ->
                    regionList = list.filter { it.parentId.isNotEmpty() }.map { it.toUI() }
                    _state.value = LocationSelectionState.ContentRegion(regionList)
                }
                .onFailure {
                    handleError(it)
                }
        }
    }

    private fun getOriginalAreasList() {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf()
            val result = locationInteractor.getOriginalAreasList(params)
            result
                .onSuccess { originalAreasList = result.getOrNull() ?: listOf() }
                .onFailure { handleError(it) }
        }
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
