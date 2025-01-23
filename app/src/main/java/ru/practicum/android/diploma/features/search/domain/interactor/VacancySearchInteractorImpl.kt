package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.search.domain.api.VacancySearchRepository
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

class VacancySearchInteractorImpl(private val repository: VacancySearchRepository) : VacancySearchInteractor {
    override suspend fun getVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): List<Vacancy> {
        return repository.searchVacancies(text = text, page = page, perPage = perPage, params = params)
    }

}
