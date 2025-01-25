package ru.practicum.android.diploma.features.vacancy.domain.api

import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

interface VacancyDetailsRepository {
    suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails>
}
