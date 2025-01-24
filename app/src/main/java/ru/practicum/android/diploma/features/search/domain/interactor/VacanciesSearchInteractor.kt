package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.search.domain.model.Vacancy

interface VacanciesSearchInteractor {
    suspend fun getVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): Result<List<Vacancy>>
}
