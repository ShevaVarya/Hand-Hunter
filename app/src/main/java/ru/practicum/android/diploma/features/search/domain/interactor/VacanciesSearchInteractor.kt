package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies

interface VacanciesSearchInteractor {
    suspend fun getVacancies(querySearch: QuerySearch): Result<Vacancies>

    fun getFilters(): FilterMainData?
}
