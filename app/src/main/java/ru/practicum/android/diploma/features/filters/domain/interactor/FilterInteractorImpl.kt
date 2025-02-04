package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.FilterInteractor

class FilterInteractorImpl(
    private val repository: FilterRepository
) : FilterInteractor {
    override fun saveSalary(salary: String) {
        repository.setSalary(salary)
    }

    override fun saveWithoutSalary(check: Boolean) {
        repository.setIsNeedToHideVacancyWithoutSalary(check)
    }

    override fun loadFilter(): FilterMainData {
        return repository.getFilterMainData()
    }

    override fun deleteFilter() {
        repository.deleteFilterMainData()
    }

    override fun deleteCountry() {
        repository.deleteCountry()
    }

    override fun deleteRegion() {
        repository.deleteRegion()
    }

    override fun deleteIndustry() {
        repository.deleteIndustry()
    }

    override fun deleteSalary() {
        repository.deleteSalary()
    }

    override fun deleteShowWithoutSalaryFlag() {
        repository.deleteShowWithoutSalaryFlag()
    }
}
