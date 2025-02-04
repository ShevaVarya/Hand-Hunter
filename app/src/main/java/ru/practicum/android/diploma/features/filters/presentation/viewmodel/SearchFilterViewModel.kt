package ru.practicum.android.diploma.features.filters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.features.common.presentation.models.toUI
import ru.practicum.android.diploma.features.filters.domain.api.FilterInteractor
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI

class SearchFilterViewModel(
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private val _stateFlowFilterUI = MutableStateFlow<FilterUI?>(FilterUI())
    val stateFlowFilterUI: StateFlow<FilterUI?> = _stateFlowFilterUI

    var baseFilterUI = FilterUI()
    var latestSearchFilterUI: FilterUI? = FilterUI()
    var oldSalary: Int? = null
    var currentFilterUI: FilterUI? = _stateFlowFilterUI.value

    fun getData() {
        val loadedData = filterInteractor.loadFilter().toUI()
        latestSearchFilterUI = FilterUI(
            country = loadedData.country?.let { it.ifEmpty { null } },
            region = loadedData.region?.let { it.ifEmpty { null } },
            industry = loadedData.industry?.let { it.ifEmpty { null } },
            salary = filterInteractor.loadFilter().toUI().salary,
            onlyWithSalary = filterInteractor.loadFilter().toUI().onlyWithSalary
        )
        _stateFlowFilterUI.value = latestSearchFilterUI
    }

    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(onlyWithSalary = onlyWithSalary)
        filterInteractor.saveWithoutSalary(check = onlyWithSalary)
    }

    fun setSalary(salary: Int?) {
        filterInteractor.saveSalary(salary = salary.toString())
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(salary = salary)
    }

    fun deletePlaceOfWork() {
        filterInteractor.deleteCountry()
        filterInteractor.deleteRegion()
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(country = null, region = null)
    }

    fun deleteIndustry() {
        filterInteractor.deleteIndustry()
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(industry = null)
    }

    fun retrySearchQueryWithFilterSearch() {
        // Метод повторяющий поисковый запрос
    }

    fun deleteSalary() {
        filterInteractor.deleteSalary()
    }

    fun deleteShowWithoutSalary() {
        filterInteractor.deleteShowWithoutSalaryFlag()
    }

    fun clearFilter() {
        filterInteractor.deleteFilter()
        _stateFlowFilterUI.value = FilterUI()
    }

    fun salaryEnterTextChanged(text: CharSequence?) {
        val newSalary = text.toString().toIntOrNull()
        if (oldSalary != newSalary) {
            oldSalary = newSalary
            setSalary(newSalary)
        }
    }
}
