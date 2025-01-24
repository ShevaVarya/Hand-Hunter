package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

class VacanciesSearchInteractorImpl(private val repository: VacanciesSearchRepository) : VacanciesSearchInteractor {
    override suspend fun getVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): Result<List<Vacancy>> {
        return repository.searchVacancies(text = text, page = page, perPage = perPage, params = params)
    }

}
