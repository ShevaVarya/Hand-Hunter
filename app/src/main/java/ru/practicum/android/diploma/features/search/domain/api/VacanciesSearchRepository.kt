package ru.practicum.android.diploma.features.search.domain.api

import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies

interface VacanciesSearchRepository {
    suspend fun searchVacancies(querySearch: QuerySearch): Result<Vacancies>
}
