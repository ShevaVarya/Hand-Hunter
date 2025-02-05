package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.presentation.viewmodel.FavouriteVacanciesViewModel
import ru.practicum.android.diploma.features.filters.presentation.ui.location.LocationSelectionViewModel
import ru.practicum.android.diploma.features.filters.presentation.ui.searchfilter.SearchFilterViewModel
import ru.practicum.android.diploma.features.filters.presentation.ui.specialization.SpecializationSelectionViewModel
import ru.practicum.android.diploma.features.filters.presentation.ui.workplace.WorkplaceSelectionViewModel
import ru.practicum.android.diploma.features.search.presentation.viewmodel.SearchViewModel
import ru.practicum.android.diploma.features.vacancy.presentation.viewmodel.VacancyInfoViewModel

val viewModelModule = module {

    viewModelOf(::FavouriteVacanciesViewModel)
    viewModelOf(::VacancyInfoViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::WorkplaceSelectionViewModel)
    viewModelOf(::SearchFilterViewModel)
    viewModelOf(::SpecializationSelectionViewModel)
    viewModel { (isCountry: Boolean) ->
        LocationSelectionViewModel(isCountry, get())
    }
}
