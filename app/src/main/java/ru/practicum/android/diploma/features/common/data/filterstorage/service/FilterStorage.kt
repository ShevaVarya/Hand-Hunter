package ru.practicum.android.diploma.features.common.data.filterstorage.service

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterCountryEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterIndustryEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterMainDataEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterRegionEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FullLocationDataEntity

interface FilterStorage {
    fun setCountry(value: FilterCountryEntity)
    fun setRegion(value: FilterRegionEntity)
    fun setIndustry(value: FilterIndustryEntity)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)

    fun getFilterMainData(): FilterMainDataEntity
    fun getFullLocationData(): FullLocationDataEntity
}

class FilterStorageImpl : FilterStorage {
    private var country: FilterCountryEntity = FilterCountryEntity.default()
    private var region: FilterRegionEntity = FilterRegionEntity.default()
    private var industry: FilterIndustryEntity = FilterIndustryEntity.default()
    private var salary: String = ""
    private var isNeedToHideVacancyWithoutSalary: Boolean = false

    override fun setCountry(value: FilterCountryEntity) {
        this.country = value
    }

    override fun setRegion(value: FilterRegionEntity) {
        this.region = value
    }

    override fun setIndustry(value: FilterIndustryEntity) {
        this.industry = value
    }

    override fun setSalary(value: String) {
        this.salary = value
    }

    override fun setIsNeedToHideVacancyWithoutSalary(value: Boolean) {
        this.isNeedToHideVacancyWithoutSalary = value
    }

    override fun getFilterMainData(): FilterMainDataEntity {
        return FilterMainDataEntity(
            country = country,
            region = region,
            industry = industry,
            salary = salary,
            isNeedToHideVacancyWithoutSalary = isNeedToHideVacancyWithoutSalary
        )
    }

    override fun getFullLocationData(): FullLocationDataEntity {
        return FullLocationDataEntity(
            country = country,
            region = region
        )
    }
}
