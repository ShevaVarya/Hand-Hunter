package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.presentation.model.VacanciesSearchUI
import ru.practicum.android.diploma.features.search.presentation.model.toUI

class VacanciesSearchInteractorImpl(
    private val repository: VacanciesSearchRepository,
    private val resourceProvider: ResourceProvider
) : VacanciesSearchInteractor {
    override suspend fun getVacancies(querySearch: QuerySearch): Result<VacanciesSearchUI> {
        return repository.searchVacancies(querySearch).mapCatching { it.toUI(resourceProvider) }
    }

}
