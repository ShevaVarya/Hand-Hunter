package ru.practicum.android.diploma.features.filters.domain.api.specialization

import ru.practicum.android.diploma.features.filters.domain.model.Industry

interface SpecializationInteractor {

    suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>>

    fun setIndustry(industry: Industry)
}
