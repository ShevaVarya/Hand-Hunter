package ru.practicum.android.diploma.features.favourite.presentation.model

import ru.practicum.android.diploma.features.search.presentation.model.VacancySearchUI

sealed interface FavouriteVacanciesState {

    data object Loading : FavouriteVacanciesState

    data object Empty : FavouriteVacanciesState

    /* Добавить, когда будут обрабатывать ошибки
    data class Error(
        val errorMessage: String
    ) : FavouriteVacanciesState
     */

    data class Content(
        val vacancies: List<VacancySearchUI>
    ) : FavouriteVacanciesState
}
