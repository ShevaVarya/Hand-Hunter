package ru.practicum.android.diploma.features.favourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor

class FavouriteVacanciesViewModel(
    private val interactor: FavouriteVacanciesInteractor,
) : ViewModel() {

    // не хочу удалять вьюМодель, а детект ругается
    fun stub () {
        val a = 0
    }

}
