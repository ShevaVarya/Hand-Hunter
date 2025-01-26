package ru.practicum.android.diploma.features.search.presentation.model

import ru.practicum.android.diploma.features.search.domain.model.Vacancies

sealed interface SearchState {
    data class Content(val vacancies: Vacancies) : SearchState
    data object Loading : SearchState
    data object Init : SearchState
    data object EmptyError : SearchState
    data object NetworkError : SearchState
    data object ServerError : SearchState
}
