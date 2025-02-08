package ru.practicum.android.diploma.features.filters.domain

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Industry
import ru.practicum.android.diploma.features.filters.domain.model.Region

interface FilterManager {
    fun keepIndustry(industry: Industry)
    fun keepSalary(salary: String)
    fun keepOnlyWithSalaryFlag(flag: Boolean)
    fun keepWorkplace(workplace: FullLocationData)

    fun saveData(data: FilterMainData)

    fun getData(): FilterMainData?

    fun deleteData()
    fun deleteWorkplace()
    fun deleteIndustry()
}

class FilterManagerImpl(
    private val filterRepository: FilterRepository
) : FilterManager {
    private var data: FilterMainData = getData() ?: FilterMainData(
        country = null,
        region = null,
        industry = null,
        salary = null,
        isNeedToHideVacancyWithoutSalary = false
    )

    override fun keepIndustry(industry: Industry) {
        data = data.copy(industry = industry)
    }

    override fun keepSalary(salary: String) {
        data = data.copy(salary = salary)
    }

    override fun keepOnlyWithSalaryFlag(flag: Boolean) {
        data = data.copy(isNeedToHideVacancyWithoutSalary = flag)
    }

    override fun keepWorkplace(workplace: FullLocationData) {
        data = data.copy(
            country = workplace.country,
            region = workplace.region
        )
    }

    override fun saveData(data: FilterMainData) {
        filterRepository.saveData(data)
    }

    override fun getData(): FilterMainData? {
        return filterRepository.getFilterMainData()
    }

    override fun deleteData() {
        filterRepository.deleteData()
        data = FilterMainData(
            country = null,
            region = null,
            industry = null,
            salary = null,
            isNeedToHideVacancyWithoutSalary = false
        )
    }

    override fun deleteWorkplace() {
        TODO("Not yet implemented")
    }

    override fun deleteIndustry() {
        TODO("Not yet implemented")
    }
}
