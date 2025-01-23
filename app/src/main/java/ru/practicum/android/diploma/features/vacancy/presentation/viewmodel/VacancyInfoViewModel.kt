package ru.practicum.android.diploma.features.vacancy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.features.vacancy.presentation.model.VacancyInfoUI

class VacancyInfoViewModel : ViewModel() {
    private var vacancyId: String = "" // Заменить на переменную в конструкторе
    private var isFavourite: Boolean = false

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun getVacancyInfo(id: String?) {
        if (id == null) {
            _state.value = State.NoData
            return
        }

        vacancyId = id
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
