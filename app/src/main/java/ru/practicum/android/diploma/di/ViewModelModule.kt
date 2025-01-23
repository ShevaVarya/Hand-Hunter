package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.presentation.viewmodel.VacancyDetailsViewModel

val viewModelModule = module {

    viewModel { VacancyDetailsViewModel(get()) }

}
