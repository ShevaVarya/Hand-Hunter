package ru.practicum.android.diploma.features.favourite.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.presentation.model.FavouriteVacanciesState
import ru.practicum.android.diploma.features.favourite.presentation.model.VacancyUI
import ru.practicum.android.diploma.utils.debounce

class FavouriteVacanciesViewModel(
    private val interactor: FavouriteVacanciesInteractor,
) : ViewModel() {

    private val state = MutableLiveData<FavouriteVacanciesState>()
    fun getState(): LiveData<FavouriteVacanciesState> = state

    init {
        loadFavourites()
    }

    fun loadFavourites() {
        viewModelScope.launch {
            interactor.getFavourites().collect { vacancies ->
                if (vacancies.isEmpty()) {
                    state.postValue(
                        FavouriteVacanciesState.Empty
                    )
                } else {
                    state.postValue(
                        FavouriteVacanciesState.Content(
                            vacancies.map { mapToVacancyUI(it) }
                        )
                    )
                }
            }
        }
    }

    private fun mapToVacancyUI(vacancy: Vacancy): VacancyUI {
        return VacancyUI(
            vacancyId = vacancy.vacancyId,
            title = vacancy.title,
            salary = vacancy.salary,
            employerName = vacancy.employerName,
            employerLogoUrl = vacancy.employerLogoUrl,
            location = vacancy.location,
            experience = vacancy.experience,
            employmentForm = vacancy.employmentForm,
            description = vacancy.description
        )
    }
}
