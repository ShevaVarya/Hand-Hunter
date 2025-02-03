package ru.practicum.android.diploma.features.selectspecialization.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustriesState

class SpecializationSelectionViewModel : ViewModel() {

    private val industriesState = MutableStateFlow<IndustriesState>(IndustriesState.Loading)
    fun getIndustriesState() = industriesState.asStateFlow()

    fun getSpecialization(industry: String) { // пустышка
        industriesState.value
    }

    fun saveSpecialization(){

    }
}
