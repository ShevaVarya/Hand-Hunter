package ru.practicum.android.diploma.features.filters.data.repository

import ru.practicum.android.diploma.features.filters.data.dto.toDomain
import ru.practicum.android.diploma.features.filters.data.dto.toEntity
import ru.practicum.android.diploma.features.filters.data.service.FilterStorage
import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Industry
import ru.practicum.android.diploma.features.filters.domain.model.Region

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage
) : FilterRepository {
    override fun setCountry(value: Country) {
        filterStorage.setCountry(value.toEntity())
    }

    override fun setRegion(value: Region) {
        filterStorage.setRegion(value.toEntity())
    }

    override fun setIndustry(value: Industry) {
        filterStorage.setIndustry(value.toEntity())
    }

    override fun setSalary(value: String) {
        filterStorage.setSalary(value)
    }

    override fun setIsNeedToHideVacancyWithoutSalary(value: Boolean) {
        filterStorage.setIsNeedToHideVacancyWithoutSalary(value)
    }

    override fun getFilterMainData(): FilterMainData? {
        return filterStorage.getFilterMainData()?.toDomain()
    }

    override fun getFullLocationData(): FullLocationData? {
        return filterStorage.getFullLocationData()?.toDomain()
    }

    override fun getSavedIndustry(): Industry? {
        return filterStorage.getSavedIndustry()?.toDomain()
    }

    override fun getCountryId(): String? {
        return filterStorage.getCountryId()
    }

    override fun deleteCountryData() {
        filterStorage.deleteCountryData()
    }

    override fun deleteRegionData() {
        filterStorage.deleteRegionData()
    }

    override fun deleteFilterMainData() {
        filterStorage.deleteFilterMainData()
    }

    override fun deleteIndustry() {
        filterStorage.deleteIndustry()
    }

    override fun deleteSalary() {
        filterStorage.deleteSalary()
    }

    override fun deleteShowWithoutSalaryFlag() {
        filterStorage.deleteShowWithoutSalaryFlag()
    }
}
