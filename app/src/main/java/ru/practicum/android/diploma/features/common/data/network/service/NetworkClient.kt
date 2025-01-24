package ru.practicum.android.diploma.features.common.data.network.service

import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity

interface NetworkClient {
    suspend fun getVacanciesList(
        text: String,
        page: Int,
        params: Map<String, String> = mapOf()
    ): Result<VacanciesEntity>

    suspend fun getVacancyById(
        id: String,
        params: Map<String, String> = mapOf()
    ): DetailsVacancyEntity
}

class NetworkClientImpl(
    private val hhApi: HHApi
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

    override suspend fun getVacancyById(id: String, params: Map<String, String>): DetailsVacancyEntity {
        return hhApi.getVacancyDetailsById(id, mapOf())
    }

    companion object {
        private const val SUCCESS_RESULT_CODE = 200
    }
}
