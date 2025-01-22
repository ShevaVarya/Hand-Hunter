package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.data.repository.FavouriteVacanciesRepositoryImpl
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository

val repositoryModule = module {

    single<FavouriteVacanciesRepository> {
        FavouriteVacanciesRepositoryImpl(get())
    }

}
