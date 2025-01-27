package ru.practicum.android.diploma.features.vacancy.data.repository

import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.vacancy.data.dto.toDomain
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository

class VacancyDetailsRepositoryImpl(
    private val networkClient: NetworkClient
) : VacancyDetailsRepository {

    override suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails> {
        return networkClient.getVacancyById(vacancyId, mapOf()).map { it.toDomain() }
    }
}
