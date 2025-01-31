package ru.practicum.android.diploma.features.search.data.repository

import ru.practicum.android.diploma.features.common.data.network.dto.toDomain
import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies

class VacanciesSearchRepositoryImpl(
    private val networkClient: NetworkClient
) : VacanciesSearchRepository {
    override suspend fun searchVacancies(querySearch: QuerySearch): Result<Vacancies> {
        return networkClient.getVacanciesList(querySearch).map { it.toDomain() }
    }
}
