package ru.practicum.android.diploma.features.favourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.presentation.model.FavouriteVacanciesState

class FavouriteVacanciesViewModel(
    private val interactor: FavouriteVacanciesInteractor,
) : ViewModel() {

    private var _state: MutableStateFlow<FavouriteVacanciesState> = MutableStateFlow(FavouriteVacanciesState.Loading)
    val state: StateFlow<FavouriteVacanciesState> = _state.asStateFlow()

}
