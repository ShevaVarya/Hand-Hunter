package ru.practicum.android.diploma.features.search.data.repository

import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.toDomain
import ru.practicum.android.diploma.features.search.domain.api.VacancySearchRepository
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

class VacancySearchRepositoryImpl(private val api: HHApi) : VacancySearchRepository {
    override suspend fun searchVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): List<Vacancy> {
        return api.getVacancies(
            text = text,
            page = page,
            perPage = perPage,
            params = params
        ).items.map { it.toDomain() }
    }
}
