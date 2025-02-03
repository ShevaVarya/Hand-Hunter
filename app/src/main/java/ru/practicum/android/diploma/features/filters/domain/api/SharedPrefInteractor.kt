package ru.practicum.android.diploma.features.filters.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion

interface SharedPrefInteractor {
    fun saveSalary(salary: String)
    fun saveWithoutSalary(check: Boolean)
    fun saveIndustry(industry: FilterIndustry)
    fun saveCountry(country: FilterCountry)
    fun saveRegion(region: FilterRegion)
    fun loadFilter(): FilterMainData
    fun deleteFilter()
}
