package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.presentation.view_model.VacancyDetailsViewModel

val viewModelModule = module {

    viewModel { VacancyDetailsViewModel(get()) }

}
