package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.SharedPrefInteractor

class SharedPrefInteractorImpl(
    private val repository: FilterRepository
) : SharedPrefInteractor {
    override fun saveSalary(salary: String) {
        repository.setSalary(salary)
    }

    override fun saveIndustry(industry: FilterIndustry) {
        repository.setIndustry(industry)
    }

    override fun saveWithoutSalary(check: Boolean) {
        repository.setIsNeedToHideVacancyWithoutSalary(check)
    }

    override fun saveCountry(country: FilterCountry) {
        repository.setCountry(country)
    }

    override fun saveRegion(region: FilterRegion) {
        repository.setRegion(region)
    }

    override fun loadFilter(): FilterMainData {
        return repository.getFilterMainData()
    }

    override fun deleteFilter() {
        repository.deleteFilterMainData()
    }
}
