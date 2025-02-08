package ru.practicum.android.diploma.features.filters.domain.manager

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData
import ru.practicum.android.diploma.features.filters.domain.model.Industry

interface FilterManager {
    fun keepWorkplace(workplace: FullLocationData)
    fun keepIndustry(industry: Industry)
    fun keepSalary(salary: String)
    fun keepOnlyWithSalaryFlag(flag: Boolean)

    fun deleteWorkplace()
    fun deleteIndustry()

    fun saveData(data: FilterMainData)
    fun resetData()
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

    override fun keepWorkplace(workplace: FullLocationData) {
        data = data.copy(
            country = workplace.country,
            region = workplace.region
        )
    }

    override fun keepIndustry(industry: Industry) {
        data = data.copy(
            industry = industry
        )
    }

    override fun keepSalary(salary: String) {
        data = data.copy(salary = salary)
    }

    override fun keepOnlyWithSalaryFlag(flag: Boolean) {
        data = data.copy(isNeedToHideVacancyWithoutSalary = flag)
    }

    override fun deleteWorkplace() {
        TODO("Not yet implemented")
    }

    override fun deleteIndustry() {
        TODO("Not yet implemented")
    }

    override fun saveData(data: FilterMainData) {
        filterRepository.saveData(data)
    }

    override fun resetData() {
        filterRepository.deleteData()
        data = FilterMainData(
            country = null,
            region = null,
            industry = null,
            salary = null,
            isNeedToHideVacancyWithoutSalary = false
        )
    }

    private fun getData(): FilterMainData? {
        return filterRepository.getFilterMainData()
    }
}
