package ru.practicum.android.diploma.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.common.presentation.models.VacancySearchUI
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.presentation.model.SearchState
import ru.practicum.android.diploma.features.search.presentation.model.VacanciesSearchUI
import ru.practicum.android.diploma.features.search.presentation.model.toUI
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val interactor: VacanciesSearchInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val searchStateFlow = MutableStateFlow<SearchState>(SearchState.Init)
    fun getSearchStateFlow() = searchStateFlow.asStateFlow()

    private var currentPage = 0
    private var totalPages = Int.MAX_VALUE
    private val loadedVacancies = mutableListOf<VacancySearchUI>()
    var isLoading = false
    private var lastSearchQuery: String? = null

    fun search(querySearch: QuerySearch, isPagination: Boolean = false) {
        val queryText = querySearch.text?.trim()

        if (queryText.isNullOrEmpty() || queryText == lastSearchQuery && !isPagination) return
        if (isLoading) return

        lastSearchQuery = querySearch.text.trim()
        isLoading = true

        viewModelScope.launch {
            try {
                if (!isPagination) searchStateFlow.emit(SearchState.Loading)
                isLoading = true
                interactor.getVacancies(querySearch)
                    .onSuccess {
                        handleSuccess(it, isPagination)
                    }
                    .onFailure {
                        handleError(it, isPagination)
                    }
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun handleSuccess(vacancies: Vacancies, isPagination: Boolean) {
        currentPage = vacancies.page
        totalPages = vacancies.pages

        val newVacancies = vacancies.items.map { it.toUI(resourceProvider) }
        if (isPagination) {
            loadedVacancies.addAll(newVacancies)
        } else {
            loadedVacancies.clear()
            loadedVacancies.addAll(newVacancies)
        }

        val vacanciesUI = VacanciesSearchUI(
            items = loadedVacancies,
            found = vacancies.found,
            pages = vacancies.pages,
            page = vacancies.page,
            perPage = vacancies.perPage
        )

        searchStateFlow.emit(SearchState.Content(vacanciesUI))
    }

    private suspend fun handleError(error: Throwable, isPagination: Boolean) {
        isLoading = false
        if (isPagination) {
            searchStateFlow.emit(
                SearchState.Content(
                    VacanciesSearchUI(
                        loadedVacancies,
                        0,
                        totalPages,
                        currentPage,
                        ITEMS_PER_PAGE
                    )
                )
            )

        } else {
            when (error) {
                is CustomException.NetworkError -> searchStateFlow.emit(SearchState.NetworkError)
                is CustomException.EmptyError -> searchStateFlow.emit(SearchState.EmptyError)
                is CustomException.ServerError -> searchStateFlow.emit(SearchState.ServerError)
                is CancellationException -> throw error
                else -> Unit
            }
        }
    }

    fun loadNextPage() {
        if (!isLoading && currentPage < totalPages) {
            viewModelScope.launch {
                searchStateFlow.emit(SearchState.Paginating)
                search(
                    QuerySearch(
                        text = lastSearchQuery,
                        page = currentPage + 1,
                        perPage = ITEMS_PER_PAGE
                    ),
                    isPagination = true
                )
            }
        }
    }

    fun onClearedSearch() {
        viewModelScope.launch {
            searchStateFlow.emit(SearchState.Init)
        }
        lastSearchQuery = null
        currentPage = 0
        totalPages = Int.MAX_VALUE
        loadedVacancies.clear()
    }

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
