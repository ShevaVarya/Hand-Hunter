package ru.practicum.android.diploma.features.filters.data.repository

import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.filters.data.dto.toDomain
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationRepository
import ru.practicum.android.diploma.features.filters.domain.model.Industry

class SpecializationRepositoryImpl(
    private val networkClient: NetworkClient
) : SpecializationRepository {

    override suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>> {
        return networkClient.getIndustriesList(params).map { list ->
            list.map { it.toDomain() }
        }
    }
}
