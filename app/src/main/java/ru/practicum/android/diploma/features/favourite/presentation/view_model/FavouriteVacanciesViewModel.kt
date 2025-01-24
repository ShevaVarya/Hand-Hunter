package ru.practicum.android.diploma.features.favourite.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.presentation.model.VacancyUI
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.utils.debounce

class FavouriteVacanciesViewModel(
    private val interactor: FavouriteVacanciesInteractor,
) : ViewModel() {

}
