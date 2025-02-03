package ru.practicum.android.diploma.features.common.domain.filter.repositoryapi

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

interface FilterRepository {
    fun setCountry(value: FilterCountry)
    fun setRegion(value: FilterRegion)
    fun setIndustry(value: FilterIndustry)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)
    fun getFilterMainData(): FilterMainData
    fun getFullLocationData(): FullLocationData
    fun deleteCountryData()
    fun deleteRegionData()
}
