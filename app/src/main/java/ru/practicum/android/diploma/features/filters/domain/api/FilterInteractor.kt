package ru.practicum.android.diploma.features.filters.domain.api

import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData

interface FilterInteractor {
    fun saveSalary(salary: String)
    fun saveWithoutSalary(check: Boolean)
    fun loadFilter(): FilterMainData
    fun deleteFilter()
    fun deleteCountryData()
    fun deleteRegionData()
    fun deleteIndustry()
    fun deleteSalary()
    fun deleteShowWithoutSalaryFlag()
}
