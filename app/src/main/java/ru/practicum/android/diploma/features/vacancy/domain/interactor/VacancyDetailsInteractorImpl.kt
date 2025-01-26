package ru.practicum.android.diploma.features.vacancy.domain.interactor

import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

class VacancyDetailsInteractorImpl(
    private val vacancyDetailsRepository: VacancyDetailsRepository
) : VacancyDetailsInteractor {
    override suspend fun getVacancyDetails(vacancyId: String): Result<VacancyDetails> {
        return vacancyDetailsRepository.getVacancyDetails(vacancyId)
    }
}
