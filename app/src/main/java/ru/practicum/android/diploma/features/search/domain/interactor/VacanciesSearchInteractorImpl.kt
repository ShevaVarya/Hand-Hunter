package ru.practicum.android.diploma.features.search.domain.interactor

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies

class VacanciesSearchInteractorImpl(
    private val repository: VacanciesSearchRepository,
    private val filterRepository: FilterRepository
) : VacanciesSearchInteractor {
    override suspend fun getVacancies(querySearch: QuerySearch): Result<Vacancies> {
        return repository.searchVacancies(querySearch).mapCatching { it }
    }

    override fun getFilters(): FilterMainData? {
        val filters = filterRepository.getFilterMainData()
        return if (checkFilters(filters)) filters else null
    }

    private fun checkFilters(filters: FilterMainData): Boolean {
        val isContented = filters.region.id.isNotEmpty() ||
            filters.country.id.isNotEmpty() ||
            filters.industry.id.isNotEmpty() ||
            filters.salary.isNotEmpty() ||
            filters.isNeedToHideVacancyWithoutSalary
        return isContented
    }

}
