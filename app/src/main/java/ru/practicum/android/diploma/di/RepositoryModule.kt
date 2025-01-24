package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.data.repository.VacanciesSearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.api.VacanciesSearchRepository

val repositoryModule = module {

    singleOf(::VacanciesSearchRepositoryImpl) bind VacanciesSearchRepository::class

}
