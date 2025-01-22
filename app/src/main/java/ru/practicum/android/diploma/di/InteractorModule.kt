package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.interactor.FavouriteVacanciesInteractorImpl

val interactorModule = module {

    single<FavouriteVacanciesInteractor> {
        FavouriteVacanciesInteractorImpl(get())
    }

}
