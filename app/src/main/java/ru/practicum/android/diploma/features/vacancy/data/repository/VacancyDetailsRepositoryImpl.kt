package ru.practicum.android.diploma.features.vacancy.data.repository

import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.vacancy.data.dto.toDomain
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class VacancyDetailsRepositoryImpl(
    private val networkClient: NetworkClient
) : VacancyDetailsRepository {

    override suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails> {
        return runCatching {
            networkClient.getVacancyById(vacancyId).getOrNull()?.toDomain() ?: VacancyDetails.stub
        }.recoverCatching {
            resolveError(it)
        }
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
