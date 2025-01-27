package ru.practicum.android.diploma.features.common.data.network.service

import retrofit2.HttpException
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity
import ru.practicum.android.diploma.features.common.domain.CustomException
import ru.practicum.android.diploma.utils.NetworkChecker
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

interface NetworkClient {
    suspend fun getVacanciesList(
        text: String,
        page: Int,
        params: Map<String, String> = mapOf()
    ): Result<VacanciesEntity>

    suspend fun getVacancyById(
        id: String,
        params: Map<String, String> = mapOf()
    ): Result<DetailsVacancyEntity>
}

class NetworkClientImpl(
    private val hhApi: HHApi,
    private val networkChecker: NetworkChecker
) : NetworkClient {
    override suspend fun getVacanciesList(
        text: String,
        page: Int,
        params: Map<String, String>
    ): Result<VacanciesEntity> {
        try {
            val response = hhApi.getVacancies(
                text = text,
                page = page,
                params = params
            )

            return if (response.resultCode == SUCCESS_RESULT_CODE) {
                val vacancies = response

                if (vacancies != null) {
                    // Код 200, тело запроса впорядке
                    Result.success(vacancies)
                } else {
                    // непредвиденная ошибка, потеря тела ответа при его успешном выполнении (код 200)
                    Result.failure(IllegalStateException("response is null"))
                }
            } else {
                // обработка кастомных ошибок с сервера
                Result.failure(IllegalStateException(response.resultCode.toString()))
            }
            // в случае, если корутина свалится, ловим ошибку (но какую?)
        } catch (exception: IllegalStateException) {
            return Result.failure(exception)
        }
    }

    override suspend fun getVacancyById(id: String, params: Map<String, String>): Result<DetailsVacancyEntity> {
        return runCatching {
            if (networkChecker.isInternetAvailable()) {
                hhApi.getVacancyDetailsById(id, params)
            } else {
                throw CustomException.NetworkError
            }
        }.recoverCatching {
            resolveError(it)
        }
    }

    private fun resolveError(error: Throwable): Nothing {
        throw when (error) {
            is CustomException.NetworkError -> error
            is CustomException.EmptyError -> error
            is CancellationException -> error
            is IOException -> CustomException.NetworkError
            is HttpException -> {
                if (error.code() in START_OF_ERROR_RANGE..END_OF_ERROR_RANGE) {
                    CustomException.RequestError(error.code())
                } else {
                    CustomException.ServerError
                }
            }

            else -> CustomException.ServerError
        }
    }

    companion object {
        private const val SUCCESS_RESULT_CODE = 200
        private const val START_OF_ERROR_RANGE = 400
        private const val END_OF_ERROR_RANGE = 499
    }
}
