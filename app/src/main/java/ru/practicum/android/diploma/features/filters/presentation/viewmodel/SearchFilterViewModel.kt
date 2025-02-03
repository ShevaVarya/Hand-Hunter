package ru.practicum.android.diploma.features.filters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.features.common.presentation.models.toDomain
import ru.practicum.android.diploma.features.common.presentation.models.toUI
import ru.practicum.android.diploma.features.filters.domain.api.SharedPrefInteractor
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.RegionUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.Industries
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustriesState
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

class SearchFilterViewModel(
    private val sharedPrefInteractor: SharedPrefInteractor
) : ViewModel() {

    private val _stateFilterUI = MutableStateFlow<FilterUI?>(FilterUI())
    val stateFilterUI: StateFlow<FilterUI?> = _stateFilterUI

    private val industriesState = MutableStateFlow<IndustriesState>(IndustriesState.Loading)
    fun getIndustriesState() = industriesState.asStateFlow()

    private val _industriesList = MutableStateFlow<List<Industries>>(emptyList())
    val industriesList: StateFlow<List<Industries>> = _industriesList

    private val _countryId = MutableStateFlow("")
    val countryId: StateFlow<String> = _countryId

    private val _chosenCountry = MutableStateFlow<CountryUI?>(CountryUI("", ""))
    val chosenCountry: StateFlow<CountryUI?> = _chosenCountry

    private val _chosenRegion = MutableStateFlow<RegionUI?>(RegionUI("", "", ""))
    val chosenRegion: StateFlow<RegionUI?> = _chosenRegion

    val latestSearchFilterUI = FilterUI()
    var oldSalary: Int? = null
    var currentFilterUI: FilterUI? = _stateFilterUI.value

    fun getData() {
        if (sharedPrefInteractor.loadFilter().toUI().country?.name?.isEmpty() ?: false ||
            sharedPrefInteractor.loadFilter().toUI().region?.name?.isEmpty() ?: false ||
            sharedPrefInteractor.loadFilter().toUI().industry?.name?.isEmpty() ?: false) {

            _stateFilterUI.value?.region = null
            _stateFilterUI.value?.country = null
            _stateFilterUI.value?.industry = null
            _stateFilterUI.value?.salary = sharedPrefInteractor.loadFilter().toUI().salary
            _stateFilterUI.value?.onlyWithSalary = sharedPrefInteractor.loadFilter().toUI().onlyWithSalary
        } else {
            _stateFilterUI.value = sharedPrefInteractor.loadFilter().toUI()
        }
    }

    fun getIndustries() {
//        industriesState.value = IndustriesState.Loading
//        Логика возвращение "Отрасль" из API
//        viewModelScope.launch { processIndustryResult(IndustriesInteractor.getIndustreas) }
    }

    // Метод заменяющий отрасль
    fun setIndustry(industry: IndustryUI?) {
        sharedPrefInteractor.saveIndustry(industry?.toDomain() ?: IndustryUI("", "").toDomain())
        _stateFilterUI.value = _stateFilterUI.value?.copy(industry = industry)
    }

    // Метод для фильтрации "не показывать без зарплаты"
    fun setOnlyWithSalary(onlyWithSalary: Boolean) {
        _stateFilterUI.value = _stateFilterUI.value?.copy(onlyWithSalary = onlyWithSalary)
        sharedPrefInteractor.saveWithoutSalary(check = onlyWithSalary)
    }

    // Метод заменяющий страну
    fun setChosenCountry(country: CountryUI?) {
        _countryId.value = country?.id ?: ""
        _chosenCountry.value = country
    }

    // Метод заменяющий регион
    fun setChosenRegion(region: RegionUI?) {
        _chosenRegion.value = region
    }

    fun setSalary(salary: Int?) {
        sharedPrefInteractor.saveSalary(salary = salary.toString())
        _stateFilterUI.value = _stateFilterUI.value?.copy(salary = salary)
    }

    // Метод очищающий граф "Место работы"
    fun clearPlaceOfWork() {
        _stateFilterUI.value = _stateFilterUI.value?.copy(country = null, region = null)

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

//    Метод возвращающий результат поиска по выбранному фильтру "отрасль"
//    private fun processIndustryResult(result: Result<List<Industries>>) {
//        if (industriesState.value != IndustriesState.Loading) return
//
//        if (result.isSuccess) {
//            _industriesList.value = result.getOrNull() ?: emptyList()
//            industriesState.value = IndustriesState.Content(result.getOrNull() ?: emptyList())
//        }
//
//        if (result.isFailure) {
//            industriesState.value = IndustriesState.Error
//        }
//    }
}
