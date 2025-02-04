package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.interactor.FavouriteVacanciesInteractorImpl
import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterInteractor
import ru.practicum.android.diploma.features.filters.domain.interactor.FilterInteractorImpl
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractorImpl
import ru.practicum.android.diploma.features.filters.domain.api.location.LocationInteractor
import ru.practicum.android.diploma.features.filters.domain.interactor.LocationInteractorImpl
import ru.practicum.android.diploma.features.filters.domain.api.workplace.SelectWorkplaceInteractor
import ru.practicum.android.diploma.features.filters.domain.interactor.SelectWorkplaceInteractorImpl
import ru.practicum.android.diploma.features.filters.domain.api.specialization.SpecializationInteractor
import ru.practicum.android.diploma.features.filters.domain.interactor.SpecializationInteractorImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsInteractor
import ru.practicum.android.diploma.features.vacancy.domain.interactor.VacancyDetailsInteractorImpl

val interactorModule = module {

    singleOf(::VacancyDetailsInteractorImpl).bind<VacancyDetailsInteractor>()
    singleOf(::VacanciesSearchInteractorImpl) bind VacanciesSearchInteractor::class
    singleOf(::FavouriteVacanciesInteractorImpl) bind FavouriteVacanciesInteractor::class
    singleOf(::FilterInteractorImpl) bind FilterInteractor::class
    singleOf(::LocationInteractorImpl) bind LocationInteractor::class
    singleOf(::SelectWorkplaceInteractorImpl) bind SelectWorkplaceInteractor::class
    singleOf(::SpecializationInteractorImpl) bind SpecializationInteractor::class
}
