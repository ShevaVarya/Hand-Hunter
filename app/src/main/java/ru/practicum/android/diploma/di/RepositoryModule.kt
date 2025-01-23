package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.data.repository.VacancyDetailsRepositoryImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository

val repositoryModule = module {
    singleOf(::VacancyDetailsRepositoryImpl).bind<VacancyDetailsRepository>()
}
