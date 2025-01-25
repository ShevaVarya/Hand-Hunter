package ru.practicum.android.diploma.features.search.data.repository

import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.toDomain
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies

class VacanciesSearchRepositoryImpl(
    private val api: HHApi,
) : VacanciesSearchRepository {
    override suspend fun searchVacancies(querySearch: QuerySearch): Result<Vacancies> {
        return runCatching {

                val vacancies = api.getVacancies(
                    text = querySearch.text ?: "",
                    page = querySearch.page,
                    perPage = querySearch.perPage,
                    params = querySearch.params
                )

                vacancies.toDomain()
        }
    }
}
