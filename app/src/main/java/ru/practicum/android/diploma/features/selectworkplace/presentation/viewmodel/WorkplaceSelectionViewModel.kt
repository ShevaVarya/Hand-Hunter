package ru.practicum.android.diploma.features.selectworkplace.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.CityUI
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocation
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocationState

class WorkplaceSelectionViewModel : ViewModel() {

    private val workplaceLocationState = MutableStateFlow<WorkplaceLocationState>(WorkplaceLocationState.Loading)
    fun getWorkplaceLocationState() = workplaceLocationState.asStateFlow()

    fun getWorkplace() {
        viewModelScope.launch {
            val country = CountryUI(id = "RU", name = "Россия")
            val city = CityUI(id = "MOW", parentId = "MOWP", name = "Москва")
            val location = WorkplaceLocation(
                country = country,
                city = city
            )
            workplaceLocationState.emit(WorkplaceLocationState.Success(location))
        }
    }
}
