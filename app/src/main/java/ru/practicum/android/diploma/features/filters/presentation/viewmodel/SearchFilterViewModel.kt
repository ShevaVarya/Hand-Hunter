package ru.practicum.android.diploma.features.filters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.features.common.presentation.models.toUI
import ru.practicum.android.diploma.features.filters.domain.api.SharedPrefInteractor
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI

class SearchFilterViewModel(
    private val sharedPrefInteractor: SharedPrefInteractor
) : ViewModel() {

    private val _stateFilterUI = MutableStateFlow<FilterUI?>(FilterUI())
    val stateFilterUI: StateFlow<FilterUI?> = _stateFilterUI

    var baseFilterUI = FilterUI()
    var latestSearchFilterUI: FilterUI? = FilterUI()
    var oldSalary: Int? = null
    var currentFilterUI: FilterUI? = _stateFilterUI.value

    fun getData() {
        val loadedData = sharedPrefInteractor.loadFilter().toUI()
        latestSearchFilterUI = FilterUI(
            country = loadedData.country?.let { it.ifEmpty {null} },
            region = loadedData.region?.let { it.ifEmpty { null } },
            industry = loadedData.industry?.let { it.ifEmpty { null } },
            salary = sharedPrefInteractor.loadFilter().toUI().salary,
            onlyWithSalary = sharedPrefInteractor.loadFilter().toUI().onlyWithSalary
        )
        _stateFilterUI.value = latestSearchFilterUI
    }

    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        _stateFilterUI.value = _stateFilterUI.value?.copy(onlyWithSalary = onlyWithSalary)
        sharedPrefInteractor.saveWithoutSalary(check = onlyWithSalary)
    }

    fun setSalary(salary: Int?) {
        sharedPrefInteractor.saveSalary(salary = salary.toString())
        _stateFilterUI.value = _stateFilterUI.value?.copy(salary = salary)
    }

    fun clearPlaceOfWork() {
        _stateFilterUI.value = _stateFilterUI.value?.copy(country = null, region = null)
    }

    fun clearIndustry() {
        _stateFilterUI.value = _stateFilterUI.value?.copy(industry = null)
    }

    fun retrySearchQueryWithFilterSearch() {
        // Метод повторяющий поисковый запрос
    }

    fun clearFilter() {
        sharedPrefInteractor.deleteFilter()
        _stateFilterUI.value = FilterUI()
    }

    fun salaryEnterTextChanged(text: CharSequence?) {
        val newSalary = text.toString().toIntOrNull()
        if (oldSalary != newSalary) {
            oldSalary = newSalary
            setSalary(newSalary)
        }
    }
}
