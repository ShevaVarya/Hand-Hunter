package ru.practicum.android.diploma.features.filters.domain.api.specialization

import ru.practicum.android.diploma.features.filters.domain.model.Industry

interface SpecializationRepository {

    suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>>

}
