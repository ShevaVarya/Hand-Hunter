package ru.practicum.android.diploma.features.filters.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.features.filters.domain.model.Filter

class SearchFilterViewModel : ViewModel() {

    private val _currentFilter = MutableLiveData<Filter>(Filter())
    val currentFilter: LiveData<Filter> = _currentFilter

    val latestSearchFilter = Filter()

    fun getIndustries() {
        // Метод возвращающий "Отрасль"
    }

    fun setIndustry(industry: IndustryUI?) {
        // Метод заменяющий отрасль
    }

    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        // Метод для фильтрации "не показывать без зарплаты"
    }

    fun setChosenCountry(country: CountryUI?) {
        // Метод заменяющий страну
    }

    fun setChosenRegion(region: RegionUI?) {
        // Метод заменяющий регион
    }

    fun setSalary(salary: Int?) {
        // Метод заменяющий сумму ожидаемой зарплаты
    }

    fun clearPlaceOfWork() {
        // Метод очищающий граф "Место работы"
    }

    fun saveFilter() {
        // Метод сохраняющий состояние "фильтра"
    }

    fun retrySearchQueryWithFilterSearch() {
        // Метод повторяющий поисковый запрос
    }

    fun clearFilter() {
        // Метод сбрасывающий настройки фильтра
    }
}
