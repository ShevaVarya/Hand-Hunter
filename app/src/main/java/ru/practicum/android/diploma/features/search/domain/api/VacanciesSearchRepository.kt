package ru.practicum.android.diploma.features.search.domain.api

import ru.practicum.android.diploma.features.search.domain.model.Vacancy

interface VacanciesSearchRepository {
    suspend fun searchVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): Result<List<Vacancy>>
}
