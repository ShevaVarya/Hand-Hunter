package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractorImpl

val interactorModule = module {

    singleOf(::VacanciesSearchInteractorImpl) bind VacanciesSearchInteractor::class

}
