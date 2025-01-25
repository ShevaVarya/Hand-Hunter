package ru.practicum.android.diploma.features.search.data.repository

import kotlinx.coroutines.CancellationException
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.toDomain
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.utils.NetworkChecker
import java.io.IOException

class VacanciesSearchRepositoryImpl(
    private val api: HHApi,
    private val networkChecker: NetworkChecker,
) : VacanciesSearchRepository {
    override suspend fun searchVacancies(querySearch: QuerySearch): Result<Vacancies> {
        return runCatching {

            if (networkChecker.isInternetAvailable()) {
                val vacancies = api.getVacancies(
                    text = querySearch.text ?: "",
                    page = querySearch.page,
                    perPage = querySearch.perPage,
                    params = querySearch.params
                )

                if (vacancies.items.isEmpty()) throw CustomException.EmptyError

                vacancies.toDomain()
            } else {
                throw CustomException.NetworkError
            }
        }
            .recoverCatching { resolveError(it) }
    }

    private fun resolveError(error: Throwable): Nothing {
        throw when (error) {
            is CustomException.NetworkError -> error
            is CustomException.EmptyError -> error
            is CancellationException -> error
            is IOException -> CustomException.NetworkError
            else -> CustomException.ServerError
        }

    }
}
