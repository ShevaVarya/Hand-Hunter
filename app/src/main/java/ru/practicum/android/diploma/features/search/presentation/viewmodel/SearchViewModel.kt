package ru.practicum.android.diploma.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
import kotlin.math.min

class SearchViewModel(
    private val interactor: VacanciesSearchInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    sealed class ToastEvent {
        data object NetworkError : ToastEvent()
        data object ServerError : ToastEvent()
    }

    private val searchStateFlow = MutableStateFlow<SearchState>(SearchState.Init)
    private val toastEventFlow = MutableSharedFlow<ToastEvent>()

    fun getSearchStateFlow() = searchStateFlow.asStateFlow()
    fun getToastEventFlow() = toastEventFlow.asSharedFlow()

    private var currentPage = 0
    private var totalPages = Int.MAX_VALUE
    private val loadedVacancies = mutableListOf<VacancySearchUI>()
    var isLoading = false
    private var lastSearchQuery: String? = null

    fun search(querySearch: QuerySearch, isPagination: Boolean = false) {
        val queryText = querySearch.text?.trim()

        if (queryText.isNullOrEmpty() || queryText == lastSearchQuery && !isPagination) return
        if (isLoading) return

        lastSearchQuery = queryText
        isLoading = true

        viewModelScope.launch {
            if (!isPagination) searchStateFlow.emit(SearchState.Loading)
            isLoading = true
            interactor.getVacancies(querySearch)
                .onSuccess {
                    handleSuccess(it, isPagination)
                }
                .onFailure {
                    handleError(it, isPagination)
                }
                .also {
                    isLoading = false
                }
        }
    }

    private suspend fun handleSuccess(vacancies: Vacancies, isPagination: Boolean) {
        currentPage = vacancies.page
        totalPages = min(vacancies.pages, MAX_ITEMS)

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
            pages = totalPages,
            page = vacancies.page,
            perPage = vacancies.perPage
        )

        searchStateFlow.emit(SearchState.Content(vacanciesUI))
    }

    private suspend fun handleError(
        throwableError: Throwable,
        isLoadingNextPage: Boolean
    ) {
        isLoading = false
        if (isLoadingNextPage) {
            searchStateFlow.emit(
                SearchState.Content(
                    VacanciesSearchUI(
                        items = loadedVacancies,
                        found = DEFAULT_TOTAL_ITEMS,
                        pages = totalPages,
                        page = currentPage,
                        perPage = ITEMS_PER_PAGE
                    )
                )
            )
            when (throwableError) {
                is CustomException.NetworkError -> toastEventFlow.emit(ToastEvent.NetworkError)
                is CustomException.ServerError -> toastEventFlow.emit(ToastEvent.ServerError)
                else -> Unit
            }
        } else {
            when (throwableError) {
                is CustomException.NetworkError -> searchStateFlow.emit(SearchState.NetworkError)
                is CustomException.EmptyError -> searchStateFlow.emit(SearchState.EmptyError)
                is CustomException.ServerError -> searchStateFlow.emit(SearchState.ServerError)
                is CancellationException -> throw throwableError
                else -> Unit
            }
        }
    }

    fun loadNextPage() {
        if (!isLoading && currentPage < totalPages) {
            viewModelScope.launch {
                searchStateFlow.emit(SearchState.Pagination)

                runCatching {
                    search(
                        QuerySearch(
                            text = lastSearchQuery,
                            page = currentPage + 1,
                            perPage = ITEMS_PER_PAGE
                        ),
                        isPagination = true
                    )
                }.onFailure { error ->
                    isLoading = false
                    when (error) {
                        is CustomException.NetworkError -> searchStateFlow.emit(SearchState.NetworkError)
                    }
                }
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
        private const val MAX_ITEMS = 100
        private const val ITEMS_PER_PAGE = 20
        private const val DEFAULT_TOTAL_ITEMS = 0

    }
}
