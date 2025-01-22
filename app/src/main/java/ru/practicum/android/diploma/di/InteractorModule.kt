package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractor

val interactorModule = module {

    single<VacancyDetailsInteractor> {
        VacancyDetailsInteractor(get())
    }
}
