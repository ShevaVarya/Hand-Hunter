package ru.practicum.android.diploma.features.filters.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.api.SharedPrefInteractor

class SharedPrefInteractorImpl(
    private val repository: FilterRepository
) : SharedPrefInteractor {
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
}
