package ru.practicum.android.diploma.features.selectworkplace.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.selectworkplace.domain.api.SelectWorkplaceInteractor
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocation
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocationState

class WorkplaceSelectionViewModel(
    private val interactor: SelectWorkplaceInteractor,
) : ViewModel() {

    private val workplaceLocationState = MutableStateFlow<WorkplaceLocationState>(WorkplaceLocationState.Init)
    fun getWorkplaceLocationState() = workplaceLocationState.asStateFlow()

    fun isWorkPlaceShowNeeded() {
        getLocation().takeIf { it.country.isNotBlank() || it.city.isNotBlank() }?.let {
            updateWorkplaceLocationState(it)
        }
    }

    fun getLocation(): WorkplaceLocation {
        val locationData = interactor.getFullLocationData()
        return WorkplaceLocation(
            country = locationData.country.name,
            city = locationData.region.name
        )
    }

    fun deleteCountryData() {
        viewModelScope.launch {
            interactor.deleteCountryData()
            updateWorkplaceLocationState(getLocation())
        }
    }

    fun deleteRegionData() {
        viewModelScope.launch {
            interactor.deleteRegionData()
            updateWorkplaceLocationState(getLocation())
        }
    }

    private fun updateWorkplaceLocationState(location: WorkplaceLocation) {
        viewModelScope.launch {
            workplaceLocationState.emit(WorkplaceLocationState.Content(location))
        }
    }
}
