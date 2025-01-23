package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractorImpl

val interactorModule = module {
    singleOf(::VacancyDetailsInteractorImpl).bind<VacancyDetailsInteractor>()
}

