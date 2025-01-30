package ru.practicum.android.diploma.features.common.data.filterstorage.repository

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.toDomain
import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage
) : FilterRepository {
    override fun setCountry(value: String) {
        filterStorage.setCountry(value)
    }

    override fun setRegion(value: String) {
        filterStorage.setRegion(value)
    }

    override fun setIndustry(value: String) {
        filterStorage.setIndustry(value)
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
}
