package ru.practicum.android.diploma.features.selectspecialization.domain.api

import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

interface SpecializationRepository {

    suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>>

}
