package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourite.data.repository.FavouriteVacanciesRepositoryImpl
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository
import ru.practicum.android.diploma.features.search.data.repository.VacancySearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacancySearchRepository

val repositoryModule = module {

    singleOf(::FavouriteVacanciesRepositoryImpl) bind FavouriteVacanciesRepository::class

    singleOf(::VacancySearchRepositoryImpl) bind VacancySearchRepository::class

}
