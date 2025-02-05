package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationInteractor
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationRepository
import ru.practicum.android.diploma.features.filters.domain.model.Industry

class SpecializationInteractorImpl(
    private val specializationRepository: SpecializationRepository,
    private val filterRepository: FilterRepository
) : SpecializationInteractor {

    override fun setIndustry(industry: Industry) {
        filterRepository.setIndustry(industry)
    }

    override suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>> {
        return specializationRepository.getIndustriesList(params)
    }
}
