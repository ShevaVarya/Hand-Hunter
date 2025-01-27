package ru.practicum.android.diploma.features.vacancy.data.repository

import ru.practicum.android.diploma.features.common.data.database.AppDatabase
import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.vacancy.data.dto.toDomain
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class VacancyDetailsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase
) : VacancyDetailsRepository {

    override suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails> {
        return runCatching {
            networkClient.getVacancyById(vacancyId, mapOf()).map { it.toDomain() }
        }.recoverCatching {
            resolveError(it)
        }
    }

    override suspend fun getFavouriteVacancy(vacancyId: String): VacancyDetails {
        val pair = appDatabase.favouritesDao().getFavouriteVacancy(vacancyId)
        return pair.first.toDomain(pair.second)
    }

    private fun resolveError(error: Throwable): Nothing {
        throw when (error) {
            is CancellationException -> error

            is IOException -> CustomException.IOException

            else -> CustomException.EmptyException
        }
    }

    sealed class CustomException : Exception() {
        object EmptyException : CustomException()
        object IOException : CustomException()
    }
}
