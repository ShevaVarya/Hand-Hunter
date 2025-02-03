package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.interactor.FavouriteVacanciesInteractorImpl
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractorImpl
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationInteractor
import ru.practicum.android.diploma.features.selectlocation.domain.interactor.LocationInteractorImpl
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationInteractor
import ru.practicum.android.diploma.features.selectspecialization.domain.interactor.SpecializationInteractorImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractorImpl

val interactorModule = module {

    singleOf(::VacancyDetailsInteractorImpl).bind<VacancyDetailsInteractor>()
    singleOf(::VacanciesSearchInteractorImpl) bind VacanciesSearchInteractor::class
    singleOf(::FavouriteVacanciesInteractorImpl) bind FavouriteVacanciesInteractor::class
    singleOf(::LocationInteractorImpl) bind LocationInteractor::class
    singleOf(::SpecializationInteractorImpl) bind SpecializationInteractor::class
}
