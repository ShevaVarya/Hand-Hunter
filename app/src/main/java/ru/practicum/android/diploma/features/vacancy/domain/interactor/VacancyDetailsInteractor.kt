package ru.practicum.android.diploma.features.vacancy.domain.interactor

import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

class VacancyDetailsInteractor(
    private val vacancyDetailsRepository: VacancyDetailsRepository
) {
    suspend operator fun invoke(vacancyId: String): VacancyDetails {
        return vacancyDetailsRepository.getVacancyDetails(vacancyId)
    }
}
