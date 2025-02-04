package ru.practicum.android.diploma.features.filters.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData

interface FilterInteractor {
    fun saveSalary(salary: String)
    fun saveWithoutSalary(check: Boolean)
    fun loadFilter(): FilterMainData
    fun deleteFilter()
    fun deleteCountry()
    fun deleteRegion()
    fun deleteIndustry()
    fun deleteSalary()
    fun deleteShowWithoutSalaryFlag()
}
