package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.common.data.filterstorage.repository.FilterRepositoryImpl
import ru.practicum.android.diploma.features.common.domain.filter.repositoryapi.FilterRepository
import ru.practicum.android.diploma.features.favourite.data.repository.FavouriteVacanciesRepositoryImpl
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository
import ru.practicum.android.diploma.features.search.data.repository.VacanciesSearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.selectlocation.data.repository.LocationRepositoryImpl
import ru.practicum.android.diploma.features.selectlocation.domain.api.LocationRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationRepository
import ru.practicum.android.diploma.features.selectspecialization.data.repository.SpecializationRepositoryImpl
import ru.practicum.android.diploma.features.vacancy.data.repository.VacancyDetailsRepositoryImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository

val repositoryModule = module {
    singleOf(::VacancyDetailsRepositoryImpl) bind VacancyDetailsRepository::class
    singleOf(::VacanciesSearchRepositoryImpl) bind VacanciesSearchRepository::class
    singleOf(::FavouriteVacanciesRepositoryImpl) bind FavouriteVacanciesRepository::class
    singleOf(::FilterRepositoryImpl) bind FilterRepository::class
    singleOf(::LocationRepositoryImpl) bind LocationRepository::class
    singleOf(::SpecializationRepositoryImpl) bind SpecializationRepository::class
}
