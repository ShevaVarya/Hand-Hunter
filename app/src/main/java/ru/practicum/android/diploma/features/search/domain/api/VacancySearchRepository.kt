package ru.practicum.android.diploma.features.search.domain.api

import ru.practicum.android.diploma.features.search.domain.model.Vacancy

interface VacancySearchRepository{
    suspend fun searchVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): List<Vacancy>
}
