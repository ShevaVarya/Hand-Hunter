package ru.practicum.android.diploma.features.filters.presentation.ui.specialization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationInteractor
import ru.practicum.android.diploma.features.filters.presentation.model.state.IndustriesState
import ru.practicum.android.diploma.features.filters.presentation.model.toDomain
import ru.practicum.android.diploma.features.filters.presentation.model.toUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.IndustryUI
import kotlin.coroutines.cancellation.CancellationException

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
                .onFailure { handleError(it) }
        }
    }

    fun selectAndSaveIndustry(industry: IndustryUI) {
        viewModelScope.launch {
            specializationInteractor.setIndustry(industry.toDomain())
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

    private fun handleError(error: Throwable) {
        when (error) {
            is CustomException.RequestError, CustomException.NetworkError, CustomException.ServerError -> {
                industriesState.value = IndustriesState.Error
            }

            is CancellationException -> throw error
            else -> Unit
        }
    }
}

