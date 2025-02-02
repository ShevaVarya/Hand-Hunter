package ru.practicum.android.diploma.features.selectlocation.data.repository

import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.selectlocation.data.dto.toDomain
import ru.practicum.android.diploma.features.selectlocation.data.dto.toFilterEntity
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationRepository
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

class LocationRepositoryImpl(
    private val networkClient: NetworkClient,
    private val filterStorage: FilterStorage
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

    override fun setCountry(country: Country) {
        filterStorage.setCountry(country.toFilterEntity())
    }

    override fun setRegion(region: Region) {
        filterStorage.setRegion(region.toFilterEntity())
    }
}
