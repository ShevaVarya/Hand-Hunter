package ru.practicum.android.diploma.features.selectspecialization.data.repository

import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.selectspecialization.data.dto.toDomain
import ru.practicum.android.diploma.features.selectspecialization.data.dto.toFilterIndustry
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

class SpecializationRepositoryImpl(
    private val filterRepository: FilterRepository,
    private val networkClient: NetworkClient
) : SpecializationRepository {

    override fun setIndustry(industry: Industry) {
        filterRepository.setIndustry(industry.toFilterIndustry())
    }

    override suspend fun getIndustriesList(params: Map<String, String>): Result<List<Industry>> {
        return networkClient.getIndustriesList(params).map { list ->
            list.map { it.toDomain() }
        }
    }
}
