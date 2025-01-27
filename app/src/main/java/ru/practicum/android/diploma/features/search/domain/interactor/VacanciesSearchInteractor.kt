package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.presentation.model.VacanciesSearchUI

interface VacanciesSearchInteractor {
    suspend fun getVacancies(querySearch: QuerySearch): Result<Vacancies>
}
