package ru.practicum.android.diploma.features.search.data.repository

import android.util.Log
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.toDomain
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

class VacanciesSearchRepositoryImpl(private val api: HHApi) : VacanciesSearchRepository {
    override suspend fun searchVacancies(
        text: String,
        page: Int,
        perPage: Int,
        params: Map<String, String>
    ): Result<List<Vacancy>> {
        return runCatching {
            api.getVacancies(
                text = text,
                page = page,
                perPage = perPage,
                params = params
            ).items.map { it.toDomain() }
        }
            .recoverCatching { resolveError(it) }
    }

    private suspend fun resolveError(error: Throwable): Nothing {
        Log.d("MyLog", "resolveError: $error")
        throw error
    }
}
