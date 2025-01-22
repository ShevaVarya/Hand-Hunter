package ru.practicum.android.diploma.features.vacancy.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

sealed class VacancyDetailsUiState {
    data object Loading : VacancyDetailsUiState()
    data class Success(val data: VacancyDetails) : VacancyDetailsUiState()
    data class Error(val message: String) : VacancyDetailsUiState()
}

class VacancyDetailsViewModel(
    private val vacancyDetailsInteractor: VacancyDetailsInteractor
) : ViewModel() {

    private val _uiState = MutableLiveData<VacancyDetailsUiState>()
    val uiState: LiveData<VacancyDetailsUiState> get() = _uiState

    fun getVacancyDetails(vacancyId: String) {
        _uiState.value = VacancyDetailsUiState.Loading
        viewModelScope.launch {
            try {
                val details = vacancyDetailsInteractor.invoke(vacancyId)
                _uiState.value = VacancyDetailsUiState.Success(details)
            } catch (e: Exception) {
                _uiState.value = VacancyDetailsUiState.Error(e.message.orEmpty())
            }
        }
    }
}
