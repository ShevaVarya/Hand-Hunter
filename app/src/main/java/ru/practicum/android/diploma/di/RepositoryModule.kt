package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.data.repository.VacanciesSearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository
import ru.practicum.android.diploma.features.vacancy.data.repository.VacancyDetailsRepositoryImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository
import ru.practicum.android.diploma.features.search.data.repository.VacancySearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacancySearchRepository

val repositoryModule = module {


    singleOf(::VacancySearchRepositoryImpl) bind VacancySearchRepository::class

    singleOf(::VacancyDetailsRepositoryImpl).bind<VacancyDetailsRepository>()
    singleOf(::VacanciesSearchRepositoryImpl) bind VacanciesSearchRepository::class
}
