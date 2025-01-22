package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.features.vacancy.data.repository.VacancyDetailsRepositoryImpl
import ru.practicum.android.diploma.features.vacancy.domain.api.VacancyDetailsRepository

val repositoryModule = module {

    single<VacancyDetailsRepository> {
        VacancyDetailsRepositoryImpl(get())
    }


}
