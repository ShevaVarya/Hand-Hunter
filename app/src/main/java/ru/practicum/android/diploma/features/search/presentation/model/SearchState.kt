package ru.practicum.android.diploma.features.search.presentation.model

sealed interface SearchState {
    data class Content(val vacancies: VacanciesSearchUI, val isSearchFilters: Boolean) : SearchState
    data object Loading : SearchState
    data object Init : SearchState
    data object EmptyError : SearchState
    data object NetworkError : SearchState
    data object ServerError : SearchState
    data object Pagination : SearchState
}
