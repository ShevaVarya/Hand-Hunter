package ru.practicum.android.diploma.features.selectlocation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.features.selectlocation.presentation.model.LocationSelectionState

class LocationSelectionViewModel(
    private val isCountry: Boolean,
    private val countryId: String?
) : ViewModel() {

    private var _state: MutableStateFlow<LocationSelectionState> =
        MutableStateFlow(LocationSelectionState.Loading)
    val state: StateFlow<LocationSelectionState> = _state.asStateFlow()

    fun search(text: String) {
        val a = 0
    }

}
