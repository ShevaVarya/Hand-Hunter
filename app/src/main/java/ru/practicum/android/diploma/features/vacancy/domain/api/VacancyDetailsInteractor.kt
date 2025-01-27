package ru.practicum.android.diploma.features.vacancy.domain.api

import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails

interface VacancyDetailsInteractor {
    suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails>
}
