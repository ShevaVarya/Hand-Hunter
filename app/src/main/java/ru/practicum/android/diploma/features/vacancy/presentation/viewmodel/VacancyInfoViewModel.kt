package ru.practicum.android.diploma.features.vacancy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.presentation.model.VacancyInfoUI
import ru.practicum.android.diploma.features.vacancy.presentation.model.toUI
import java.io.IOException

class VacancyInfoViewModel(
    private val resourceProvider: ResourceProvider,
    private val vacancyDetailsInteractor: VacancyDetailsInteractor,
    private val favouriteVacanciesInteractor: FavouriteVacanciesInteractor,
    private val vacancyId: String?
) : ViewModel() {
    private var isFavourite: Boolean = false
    private var details: VacancyDetails? = null

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun getVacancyInfo() {
        if (vacancyId == null) {
            _state.value = State.NoData
            return
        }

        viewModelScope.launch {
            try {
                details = vacancyDetailsInteractor.getVacancyDetails(vacancyId).getOrNull()
                details?.let {
                    _state.value = State.Data(it.toUI(resourceProvider))
                }
            } catch (e: IOException) {
                println(e)
                _state.value = State.ServerError
            }
        }
    }

    fun getVacancySharingText(): String {
        return EMPTY_STRING
    }

    fun toggleFavouriteVacancy(): Boolean {
        if (_state.value is State.Data) {
            viewModelScope.launch {
                details?.let {
                    favouriteVacanciesInteractor.addToFavourites(it)
                }
            }
            isFavourite = !isFavourite
        }
        return isFavourite
    }

    override fun onCleared() {
        super.onCleared()
        details = null
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
