package ru.practicum.android.diploma.features.filters.presentation.ui.searchfilter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterInteractor
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationInteractor
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationInteractor
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.presentation.model.state.SearchFilterState
import ru.practicum.android.diploma.features.filters.presentation.model.toUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.FilterUI

class SearchFilterViewModel(
    private val filterInteractor: FilterInteractor,
    private val specializationInteractor: SpecializationInteractor,
    private val locationInteractor: LocationInteractor,
) : ViewModel() {

    private val _stateFlowFilterUI = MutableStateFlow(SearchFilterState.Content(FilterUI()))
    val stateFlowFilterUI = _stateFlowFilterUI.asStateFlow()

    private var currentSearchFilter: FilterMainData = filterInteractor.loadFilter()
    private var latestSearchFilterUI: FilterUI = FilterUI()
    var oldSalary: String? = null
        private set

    fun getData() {
        latestSearchFilterUI = filterInteractor.loadFilter().toUI()
        val isVisible = isVisibleAcceptButton()
        _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisible)
    }

    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        latestSearchFilterUI = latestSearchFilterUI.copy(onlyWithSalary = onlyWithSalary)
        _stateFlowFilterUI.value =
            SearchFilterState.Content(
                latestSearchFilterUI,
                isVisibleAcceptButton()
            )
        filterInteractor.saveWithoutSalary(check = onlyWithSalary)
    }

    private fun setSalary(salary: String?) {
        if (!salary.isNullOrBlank()) {
            latestSearchFilterUI = latestSearchFilterUI.copy(salary = salary)
            _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisibleAcceptButton())
            filterInteractor.saveSalary(salary = salary)
        }
    }

    fun resetAllChanges() {
        viewModelScope.launch {
            currentSearchFilter.let {
                if (it.country == null && it.region == null) {
                    deletePlaceOfWork()
                } else {
                    it.country?.let {
                        locationInteractor.setCountry(it)
                    }
                    it.region?.let {
                        locationInteractor.setRegion(it)
                    }
                }
                if (it.industry != null) {
                    specializationInteractor.setIndustry(it.industry)
                } else {
                    deleteIndustry()
                }
                if (it.salary.isNullOrEmpty()) {
                    deleteSalary()
                } else {
                    setSalary(it.salary)
                }
                setOnlyWithSalary(it.isNeedToHideVacancyWithoutSalary)
            }
        }
    }

    private fun isVisibleAcceptButton(): Boolean {
        return currentSearchFilter.toUI() != latestSearchFilterUI
    }

    fun deletePlaceOfWork() {
        filterInteractor.deleteCountryData()
        filterInteractor.deleteRegionData()
        latestSearchFilterUI = latestSearchFilterUI.copy(country = null, region = null)
        _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisibleAcceptButton())
    }

    fun deleteIndustry() {
        filterInteractor.deleteIndustry()
        latestSearchFilterUI = latestSearchFilterUI.copy(industry = null)
        _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisibleAcceptButton())
    }

    fun deleteSalary() {
        filterInteractor.deleteSalary()
        latestSearchFilterUI = latestSearchFilterUI.copy(salary = null)
        _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisibleAcceptButton())
    }

    fun deleteShowWithoutSalary() {
        filterInteractor.deleteShowWithoutSalaryFlag()
        latestSearchFilterUI = latestSearchFilterUI.copy(onlyWithSalary = false)
        _stateFlowFilterUI.value = SearchFilterState.Content(latestSearchFilterUI, isVisibleAcceptButton())
    }

    fun clearFilter() {
        filterInteractor.deleteFilter()
        latestSearchFilterUI = FilterUI()
        currentSearchFilter = filterInteractor.loadFilter()
        _stateFlowFilterUI.value = SearchFilterState.Content(FilterUI(), isVisibleAcceptButton())
    }

    fun salaryEnterTextChanged(text: CharSequence?) {
        if (text == null) return
        val newSalary = text.toString().trim()
        if (oldSalary != newSalary) {
            oldSalary = newSalary
            setSalary(newSalary)
        }
    }
}
