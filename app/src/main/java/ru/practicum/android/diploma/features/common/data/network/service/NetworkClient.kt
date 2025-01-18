package ru.practicum.android.diploma.features.common.data.network.service

import retrofit2.awaitResponse
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity

interface NetworkClient {
    suspend fun getVacanciesList(page: Int, params: Map<String, String> = mapOf()): Result<VacanciesEntity>
}

class NetworkClientImpl(private val hhApi: HHApi) : NetworkClient {
    override suspend fun getVacanciesList(page: Int, params: Map<String, String>): Result<VacanciesEntity> {
        // можем в корутине сразу обрабатывать ошибку через tryLaunch()
        try {
            val response = hhApi.getVacancies(
                page = page,
                params = params
            ).awaitResponse()

            return if (response.isSuccessful) {
                val vacancies = response.body()

                if (vacancies != null) {
                    Result.success(vacancies)
                } else {
                    Result.failure(IllegalStateException("response is null"))
                }
            } else {
                Result.failure(IllegalStateException(response.errorBody().toString()))
            }
        } catch (exception: IllegalStateException) {
            return Result.failure(exception)
        }
    }
}
