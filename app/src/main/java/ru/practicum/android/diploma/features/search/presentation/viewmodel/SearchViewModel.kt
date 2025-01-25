package ru.practicum.android.diploma.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.presentation.model.SearchState
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val interactor: VacanciesSearchInteractor
) : ViewModel() {

    private val searchStateFlow = MutableStateFlow<SearchState>(SearchState.Init)
    fun getSearchStateFlow() = searchStateFlow.asStateFlow()

    private var lastSearchQuery: String? = null

    fun search(querySearch: QuerySearch) {
        if (querySearch.text.isNullOrBlank() || querySearch.text == lastSearchQuery) return

        lastSearchQuery = querySearch.text
        viewModelScope.launch {
            searchStateFlow.emit(SearchState.Loading)
            interactor.getVacancies(querySearch)
                .onSuccess { searchStateFlow.emit(SearchState.Content(it)) }
                .onFailure { handleError(it) }
        }

    }

    private suspend fun handleError(error: Throwable) {
        when (error) {
            is CustomException.NetworkError -> searchStateFlow.emit(SearchState.NetworkError)
            is CustomException.EmptyError -> searchStateFlow.emit(SearchState.EmptyError)
            is CustomException.ServerError -> searchStateFlow.emit(SearchState.ServerError)
            is CancellationException -> throw error
            else -> Unit
        }
    }


}
