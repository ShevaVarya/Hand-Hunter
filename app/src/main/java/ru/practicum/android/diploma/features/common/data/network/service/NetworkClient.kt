package ru.practicum.android.diploma.features.common.data.network.service

import retrofit2.awaitResponse
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity

interface NetworkClient {
    suspend fun getVacanciesList(page: Int, params: Map<String, String> = mapOf()): Result<VacanciesEntity>
}

class NetworkClientImpl(
    private val hhApi: HHApi
) : NetworkClient {
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
                    // Код 200, тело запроса впорядке
                    Result.success(vacancies)
                } else {
                    // непредвиденная ошибка, потеря тела ответа при его успешном выполнении (код 200)
                    Result.failure(IllegalStateException("response is null"))
                }
            } else {
                // обработка кастомных ошибок с сервера
                Result.failure(IllegalStateException(response.errorBody().toString()))
            }
            // в случае, если корутина свалится, ловим ошибку (но какую?)
        } catch (exception: IllegalStateException) {
            return Result.failure(exception)
        }
    }
}
