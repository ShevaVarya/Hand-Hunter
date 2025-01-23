package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.data.repository.VacancySearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacancySearchRepository

val repositoryModule = module {

    singleOf(::VacancySearchRepositoryImpl) bind VacancySearchRepository::class

}
