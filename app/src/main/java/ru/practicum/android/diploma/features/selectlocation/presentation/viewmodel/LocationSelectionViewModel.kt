package ru.practicum.android.diploma.features.selectlocation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationInteractor
import ru.practicum.android.diploma.features.selectlocation.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.LocationSelectionState
import ru.practicum.android.diploma.features.selectlocation.presentation.model.RegionUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.Regionable
import ru.practicum.android.diploma.features.selectlocation.presentation.model.fromUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.toUI
import kotlin.coroutines.cancellation.CancellationException

class LocationSelectionViewModel(
    private val isCountry: Boolean,
    private val countryId: String?,
    private val locationInteractor: LocationInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<LocationSelectionState>(LocationSelectionState.Loading)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun search(text: String) {
        val a = 0
    }

    fun saveRegion(region: Regionable) {
        if (isCountry) {
            locationInteractor.setCountry(
                (region as CountryUI).fromUI()
            )
        } else {
            locationInteractor.setRegion(
                (region as RegionUI).fromUI()
            )
        }

    }

    private fun getData() {
        if (isCountry) {
            getCountryList()
        } else {
            getRegionList()
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

    private fun getRegionList() {
        viewModelScope.launch {
            val params = countryId?.takeIf { it.isNotEmpty() }?.let {
                mapOf("countryId" to it)
            } ?: mapOf()

            val result = if (countryId.isNullOrEmpty()) {
                locationInteractor.getAllAreasList(params)
            } else {
                locationInteractor.getAllAreasByIdList(countryId, params)
            }

            result
                .onSuccess { list ->
                    val filteredList = list.filter { it.parentId.isNotEmpty() }
                    _state.value = LocationSelectionState.ContentRegion(filteredList.map { it.toUI() })
                }
                .onFailure {
                    handleError(it)
                }
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
}
