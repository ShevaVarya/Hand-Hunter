package ru.practicum.android.diploma.features.filters.data.repository

import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.filters.data.dto.toDomain
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationRepository
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Region


class LocationRepositoryImpl(
    private val networkClient: NetworkClient
) : LocationRepository {
    override suspend fun getCountriesList(params: Map<String, String>): Result<List<Country>> {
        return networkClient.getCountriesList(params).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getAllAreasList(params: Map<String, String>): Result<List<Region>> {
        return networkClient.getAllAreasList(params).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getAllAreasByIdList(countryId: String, params: Map<String, String>): Result<List<Region>> {
        return networkClient.getAllAreasByIdList(countryId, params).map { list ->
            list.map { it.toDomain() }
        }
    }
}
