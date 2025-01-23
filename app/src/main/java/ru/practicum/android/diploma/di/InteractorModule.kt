package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.interactor.FavouriteVacanciesInteractorImpl
import ru.practicum.android.diploma.features.search.domain.interactor.VacancySearchInteractor
import ru.practicum.android.diploma.features.search.domain.interactor.VacancySearchInteractorImpl

val interactorModule = module {

    single<FavouriteVacanciesInteractor> {
        FavouriteVacanciesInteractorImpl(get())
    }

    singleOf(::VacancySearchInteractorImpl) bind VacancySearchInteractor::class

}
