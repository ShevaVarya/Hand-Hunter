package ru.practicum.android.diploma.features.filters.domain.api.filter

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.domain.model.FilterRegion
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Industry

@Suppress("TooManyFunctions")
interface FilterRepository {
    fun setCountry(value: Country)
    fun setRegion(value: FilterRegion)
    fun setIndustry(value: Industry)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)
    fun getFilterMainData(): FilterMainData?
    fun getFullLocationData(): FullLocationData?
    fun deleteFilterMainData()
    fun deleteIndustry()
    fun deleteSalary()
    fun deleteShowWithoutSalaryFlag()
    fun getCountryId(): String?
    fun deleteCountryData()
    fun deleteRegionData()
}
