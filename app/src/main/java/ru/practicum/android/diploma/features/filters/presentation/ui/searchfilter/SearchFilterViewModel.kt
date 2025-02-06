package ru.practicum.android.diploma.features.filters.presentation.ui.searchfilter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterInteractor
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationInteractor
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationInteractor
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.presentation.model.toUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.FilterUI

class SearchFilterViewModel(
    private val filterInteractor: FilterInteractor,
    private val specializationInteractor: SpecializationInteractor,
    private val locationInteractor: LocationInteractor,
) : ViewModel() {

    private val _stateFlowFilterUI = MutableStateFlow<FilterUI?>(FilterUI())
    val stateFlowFilterUI: StateFlow<FilterUI?> = _stateFlowFilterUI

    val currentSearchFilterUI: FilterMainData = filterInteractor.loadFilter()
    private var latestSearchFilterUI: FilterUI? = FilterUI()
    var oldSalary: String? = null
        private set

    fun getData() {
        val loadedData = filterInteractor.loadFilter().toUI()

        latestSearchFilterUI = FilterUI(
            country = loadedData.country?.let { it.ifEmpty { null } },
            region = loadedData.region?.let { it.ifEmpty { null } },
            industry = loadedData.industry?.let { it.ifEmpty { null } },
            salary = loadedData.salary?.let { it.ifEmpty { null } },
            onlyWithSalary = loadedData.onlyWithSalary
        )
        _stateFlowFilterUI.value = latestSearchFilterUI
    }

    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(onlyWithSalary = onlyWithSalary)
        filterInteractor.saveWithoutSalary(check = onlyWithSalary)
    }

    private fun setSalary(salary: String?) {
        if (!salary.isNullOrBlank()) {
            filterInteractor.saveSalary(salary = salary)
            _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(salary = salary)
        }
    }

    fun resetAllChanges() {
        viewModelScope.launch {
            locationInteractor.setCountry(currentSearchFilterUI.country)
            locationInteractor.setRegion(currentSearchFilterUI.region)
            specializationInteractor.setIndustry(currentSearchFilterUI.industry)
            setSalary(currentSearchFilterUI.salary)
            setOnlyWithSalary(currentSearchFilterUI.isNeedToHideVacancyWithoutSalary)
        }
    }

    fun isVisibleAcceptButton(filterUI: FilterUI?): Boolean {
        val changeFilterUI = FilterUI(
            country = currentSearchFilterUI.toUI().country?.let { it.ifEmpty { null } },
            region = currentSearchFilterUI.toUI().region?.let { it.ifEmpty { null } },
            industry = currentSearchFilterUI.toUI().industry?.let { it.ifEmpty { null } },
            salary = currentSearchFilterUI.toUI().salary?.let { it.ifEmpty { null } },
            onlyWithSalary = currentSearchFilterUI.toUI().onlyWithSalary
        )
        return filterUI != changeFilterUI
    }

    fun deletePlaceOfWork() {
        filterInteractor.deleteCountryData()
        filterInteractor.deleteRegionData()
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(country = null, region = null)
    }

    fun deleteIndustry() {
        filterInteractor.deleteIndustry()
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(industry = null)
    }

    fun deleteSalary() {
        filterInteractor.deleteSalary()
        _stateFlowFilterUI.value = _stateFlowFilterUI.value?.copy(salary = null)

    }

    fun deleteShowWithoutSalary() {
        filterInteractor.deleteShowWithoutSalaryFlag()
    }

    fun clearFilter() {
        filterInteractor.deleteFilter()
        _stateFlowFilterUI.value = FilterUI()
    }

    fun salaryEnterTextChanged(text: CharSequence?) {
        if (text == null) return
        val newSalary = text.toString()
        if (oldSalary != newSalary) {
            oldSalary = newSalary
            setSalary(newSalary)
        }
    }
}
