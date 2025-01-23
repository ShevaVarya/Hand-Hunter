package ru.practicum.android.diploma.features.vacancy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.features.vacancy.presentation.model.VacancyInfoUI

class VacancyInfoViewModel : ViewModel() {
    private var vacancyId: String = ""
    private var isFavourite: Boolean = false

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state

    fun getVacancyInfo(id: String?) {
        if (id == null) {
            _state.value = State.NoData
            return
        }
        vacancyId = id
    }

    fun getVacancySharingText(): String {
        return ""
    }

    fun toggleFavouriteVacancy(): Boolean {
        // логика добавления в избранное
        isFavourite = !isFavourite
        return isFavourite
    }

    sealed interface State {
        class Data(val vacancyInfo: VacancyInfoUI) : State
        data object NoData : State
        data object ServerError : State
        data object Loading : State
    }
}
