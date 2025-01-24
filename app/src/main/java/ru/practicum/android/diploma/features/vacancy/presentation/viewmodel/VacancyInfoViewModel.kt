package ru.practicum.android.diploma.features.vacancy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.presentation.model.VacancyInfoUI
import ru.practicum.android.diploma.features.vacancy.presentation.model.toUI

class VacancyInfoViewModel(
    private val resourceProvider: ResourceProvider,
    private val vacancyDetailsInteractor: VacancyDetailsInteractor,
    private val vacancyId: String?
) : ViewModel() {
    private var isFavourite: Boolean = false

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun getVacancyInfo() {
        if (vacancyId == null) {
            _state.value = State.NoData
            return
        }

        viewModelScope.launch {
            try {
                val details = vacancyDetailsInteractor.getVacancyDetails(vacancyId).getOrNull()
                if (details != null){
                    _state.value = State.Data(details.toUI(resourceProvider))
                }
            } catch (e: IllegalStateException) {
                _state.value = State.ServerError
            }
        }
    }

    fun getVacancySharingText(): String {
        return EMPTY_STRING
    }

    fun toggleFavouriteVacancy(): Boolean {
        // логика добавления в избранное
        isFavourite = !isFavourite
        return isFavourite
    }

    sealed interface State {
        data class Data(val vacancyInfo: VacancyInfoUI) : State
        data object NoData : State
        data object ServerError : State
        data object Loading : State
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
