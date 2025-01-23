package ru.practicum.android.diploma.features.vacancy.domain.interactor

import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

interface VacancyDetailsInteractor {
    suspend operator fun invoke(vacancyId: String): VacancyDetails
}
