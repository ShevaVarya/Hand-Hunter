package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.presentation.view_model.FavouriteVacanciesViewModel
import ru.practicum.android.diploma.features.vacancy.presentation.viewmodel.VacancyInfoViewModel

val viewModelModule = module {

    viewModel {
        FavouriteVacanciesViewModel(get())
    }
    viewModelOf(::VacancyInfoViewModel)
}
