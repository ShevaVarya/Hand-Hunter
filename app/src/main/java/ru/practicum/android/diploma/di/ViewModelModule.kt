package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.presentation.view_model.FavouriteVacanciesViewModel

val viewModelModule = module {

    viewModel {
        FavouriteVacanciesViewModel(get())
    }
}
