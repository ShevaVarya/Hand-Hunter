package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.presentation.viewmodel.VacancyDetailsViewModel
import ru.practicum.android.diploma.features.vacancy.presentation.viewmodel.VacancyInfoViewModel

val viewModelModule = module {

    viewModel { VacancyDetailsViewModel(get()) }

    viewModelOf(::VacancyInfoViewModel)
}
