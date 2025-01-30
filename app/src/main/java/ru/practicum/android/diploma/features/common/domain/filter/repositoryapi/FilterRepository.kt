package ru.practicum.android.diploma.features.common.domain.filter.repositoryapi

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

interface FilterRepository {
    fun setCountry(value: String)
    fun setRegion(value: String)
    fun setIndustry(value: String)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)
    fun getFilterMainData(): FilterMainData
    fun getFullLocationData(): FullLocationData
}
