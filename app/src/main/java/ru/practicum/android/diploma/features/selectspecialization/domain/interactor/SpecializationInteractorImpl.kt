package ru.practicum.android.diploma.features.selectspecialization.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationInteractor
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.api.toFilterIndustry
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

class SpecializationInteractorImpl(
    private val specializationRepository: SpecializationRepository,
    private val filterRepository: FilterRepository
) : SpecializationInteractor {

    override fun setIndustry(industry: Industry) {
        filterRepository.setIndustry(industry.toFilterIndustry())
    }

    override suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>> {
        return specializationRepository.getIndustriesList(params)
    }
}
