package ru.practicum.android.diploma.features.common.data.filterstorage.repository

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.toDomain
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.toEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage
) : FilterRepository {
    override fun setCountry(value: FilterCountry) {
        filterStorage.setCountry(value.toEntity())
    }

    override fun setRegion(value: FilterRegion) {
        filterStorage.setRegion(value.toEntity())
    }

    override fun setIndustry(value: FilterIndustry) {
        filterStorage.setIndustry(value.toEntity())
    }

    override fun setSalary(value: String) {
        filterStorage.setSalary(value)
    }

    override fun setIsNeedToHideVacancyWithoutSalary(value: Boolean) {
        filterStorage.setIsNeedToHideVacancyWithoutSalary(value)
    }

    override fun getFilterMainData(): FilterMainData {
        return filterStorage.getFilterMainData().toDomain()
    }

    override fun getFullLocationData(): FullLocationData {
        return filterStorage.getFullLocationData().toDomain()
    }

    override fun getCountryId(): String {
        return filterStorage.getCountryId()
    }

    override fun deleteFilterMainData() {
        filterStorage.deleteFilterMainData()
    }
}
