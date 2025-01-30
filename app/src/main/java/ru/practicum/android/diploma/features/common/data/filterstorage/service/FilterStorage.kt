package ru.practicum.android.diploma.features.common.data.filterstorage.service

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterMainDataEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FullLocationDataEntity

interface FilterStorage {
    fun setCountry(value: String)
    fun setRegion(value: String)
    fun setIndustry(value: String)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)

    fun getFilterMainData(): FilterMainDataEntity
    fun getFullLocationData(): FullLocationDataEntity
}

class FilterStorageImpl : FilterStorage {
    private var country: String = ""
    private var region: String = ""
    private var industry: String = ""
    private var salary: String = ""
    private var isNeedToHideVacancyWithoutSalary: Boolean = false

    override fun setCountry(value: String) {
        this.country = value
    }

    override fun setRegion(value: String) {
        this.region = value
    }

    override fun setIndustry(value: String) {
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
