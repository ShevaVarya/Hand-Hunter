package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractorImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractorImpl

val interactorModule = module {
    singleOf(::VacancyDetailsInteractorImpl).bind<VacancyDetailsInteractor>()
    singleOf(::VacanciesSearchInteractorImpl) bind VacanciesSearchInteractor::class
}
