package ru.practicum.android.diploma.features.selectworkplace.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocation
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocationState

class WorkplaceSelectionViewModel(
    private val filterRepository: FilterRepository,
) : ViewModel() {

    private val workplaceLocationState = MutableStateFlow<WorkplaceLocationState>(WorkplaceLocationState.Init)
    fun getWorkplaceLocationState() = workplaceLocationState.asStateFlow()

    fun isWorkPlaceShowNeeded() {
        val country = getCountry()
        val region = getCity()
        if (country.isNotBlank() || region.isNotBlank()) {
            getWorkplace(country, region)
        }
    }

    fun getLocation(): WorkplaceLocation {
        return WorkplaceLocation(
            country = getCountry(),
            city = getCity()
        )
    }

    fun deleteCountryData() {
        viewModelScope.launch {
            filterRepository.deleteCountryData()
            workplaceLocationState.emit(
                createWorkplaceLocationState(getCountry(), getCity())
            )
        }
    }

    fun deleteRegionData() {
        viewModelScope.launch {
            filterRepository.deleteRegionData()
            workplaceLocationState.emit(
                createWorkplaceLocationState(getCountry(), getCity())
            )
        }
    }

    private fun getCountry(): String {
        return filterRepository.getFullLocationData().country.name
    }

    private fun getCity(): String {
        return filterRepository.getFullLocationData().region.name
    }

    private fun getWorkplace(country: String, region: String) {
        viewModelScope.launch {
            workplaceLocationState.emit(
                createWorkplaceLocationState(country, region)
            )
        }
    }

    private fun createWorkplaceLocationState(country: String, region: String): WorkplaceLocationState.Content {
        return WorkplaceLocationState.Content(
            WorkplaceLocation(
                country = country,
                city = region
            )
        )
    }
}
