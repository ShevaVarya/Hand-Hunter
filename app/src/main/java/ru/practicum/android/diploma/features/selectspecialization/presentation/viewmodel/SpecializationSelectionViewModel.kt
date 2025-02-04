package ru.practicum.android.diploma.features.selectspecialization.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationInteractor
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustriesState
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.fromUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.toUI

class SpecializationSelectionViewModel(
    private val specializationInteractor: SpecializationInteractor
) : ViewModel() {

    init {
        getIndustries()
    }

    private var fullIndustriesList: List<IndustryUI> = emptyList()

    val selectedIndustry = MutableLiveData<IndustryUI?>(null)

    private val industriesState = MutableStateFlow<IndustriesState>(IndustriesState.Loading)
    fun getIndustriesState() = industriesState.asStateFlow()

    private fun getIndustries() {
        viewModelScope.launch {
            specializationInteractor.getIndustriesList(mapOf())
                .onSuccess { list ->
                    fullIndustriesList = list.map { it.toUI() }
                    industriesState.value = IndustriesState.Content(fullIndustriesList)
                }
                .onFailure { industriesState.value = IndustriesState.Error }
        }
    }

    fun selectAndSaveIndustry(industry: IndustryUI) {
        viewModelScope.launch {
            specializationInteractor.setIndustry(industry.fromUI())
        }
    }

    fun search(text: String) {
        val filteredList = if (text.isBlank()) {
            fullIndustriesList
        } else {
            fullIndustriesList.filter { it.name.contains(text, ignoreCase = true) }
        }

        industriesState.value = IndustriesState.Content(filteredList)
    }
}

