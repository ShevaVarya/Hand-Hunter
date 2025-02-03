package ru.practicum.android.diploma.features.filters.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData

interface SharedPrefInteractor {
    fun saveSalary(salary: String)
    fun saveWithoutSalary(check: Boolean)
    fun loadFilter(): FilterMainData
    fun deleteFilter()
}
