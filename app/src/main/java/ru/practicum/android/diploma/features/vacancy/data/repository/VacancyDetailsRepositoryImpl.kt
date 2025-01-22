package ru.practicum.android.diploma.features.vacancy.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.vacancy.data.dto.toDomain
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

class VacancyDetailsRepositoryImpl(
    private val hhApi: HHApi
) : VacancyDetailsRepository {

    override suspend fun getVacancyDetails(vacancyId: String): VacancyDetails {
        return withContext(Dispatchers.IO) {
            try {
                val response = hhApi.getVacancyDetailsById(vacancyId, emptyMap<String, String>()).execute()
                if (response.isSuccessful) {
                    val vacancyDetails = response.body()?.toDomain()
                        ?: throw IllegalArgumentException("Response is null")
                    Log.d("VacancyRepository", "API Response: ${response.body()}")
                    Log.d("VacancyRepository", "Domain Model: $vacancyDetails")
                    vacancyDetails
                } else {
                    throw HttpException(response)
                }
            } catch (e: Exception) {
                Log.e("VacancyRepository", "Error fetching vacancy details", e)
                throw e
            }
        }
    }
}
