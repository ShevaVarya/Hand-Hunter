package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.domain.interactor.VacancySearchInteractorImpl
import ru.practicum.android.diploma.features.search.domain.interactor.VacancySearchInteractor

val interactorModule = module {

    singleOf(::VacancySearchInteractorImpl) bind VacancySearchInteractor::class

}
