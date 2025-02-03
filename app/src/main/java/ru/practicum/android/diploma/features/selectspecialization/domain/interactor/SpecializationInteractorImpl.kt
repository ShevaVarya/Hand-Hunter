package ru.practicum.android.diploma.features.selectspecialization.domain.interactor

import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationInteractor
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

class SpecializationInteractorImpl(
    private val specializationRepository: SpecializationRepository
) : SpecializationInteractor {

    override fun setIndustry(industry: Industry) {
        return specializationRepository.setIndustry(industry)
    }
}
