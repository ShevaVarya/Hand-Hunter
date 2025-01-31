package ru.practicum.android.diploma.features.filters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI

class SearchFilterViewModel : ViewModel() {

    private val _stateFilterUI = MutableStateFlow<FilterUI>(FilterUI())
    val stateFilterUI: StateFlow<FilterUI> = _stateFilterUI

    val latestSearchFilterUI = FilterUI()
    private var oldSalary: Int? = null
    var currentFilterUI = FilterUI()

    fun getIndustries() {
        // Метод возвращающий "Отрасль"
    }

    // Метод заменяющий отрасль
//    fun setIndustry(industry: IndustryUI?) {
//
//    }

    // Метод для фильтрации "не показывать без зарплаты"
//    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
//
//    }

    // Метод заменяющий страну
//    fun setChosenCountry(country: CountryUI?) {
//
//    }

    // Метод заменяющий регион
//    fun setChosenRegion(region: RegionUI?) {
//
//    }

    fun setSalary(salary: Int?) {
        // Метод заменяющий сумму ожидаемой зарплаты
    }

    // Метод очищающий граф "Место работы"
//    fun clearPlaceOfWork() {
//
//    }

    fun saveFilter() {
        // Метод сохраняющий состояние "фильтра"
    }

    fun retrySearchQueryWithFilterSearch() {
        // Метод повторяющий поисковый запрос
    }

    fun clearFilter() {
        // Метод сбрасывающий настройки фильтра
    }

    fun salaryEnterTextChanged(text: CharSequence?) {
        val newSalary = text.toString().toIntOrNull()
        if (oldSalary != newSalary) {
            oldSalary = newSalary
            setSalary(newSalary)
        }
    }
}
