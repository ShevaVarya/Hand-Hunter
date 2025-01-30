package ru.practicum.android.diploma.features.selectworkplace.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.AreaUI
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocation
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocationState

class WorkplaceSelectionViewModel : ViewModel() {

    private val workplaceLocationState = MutableStateFlow<WorkplaceLocationState>(WorkplaceLocationState.Loading)
    fun getWorkplaceLocationState() = workplaceLocationState.asStateFlow()

    fun getWorkplace() {
        viewModelScope.launch {

            val country = CountryUI(id = "RU", name = "Россия")
            val city = AreaUI(id = "MOW", name = "Москва")


            val location = WorkplaceLocation(
                country = country,
                area = city
            )
            workplaceLocationState.emit(WorkplaceLocationState.Success(location))

        }
    }
}
