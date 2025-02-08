package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.common.presentation.ResourceProviderImpl
import ru.practicum.android.diploma.features.filters.domain.FilterManager
import ru.practicum.android.diploma.features.filters.domain.FilterManagerImpl
import ru.practicum.android.diploma.features.filters.domain.LocationManager
import ru.practicum.android.diploma.features.filters.domain.LocationManagerImpl

val utilsModule = module {
    single<ResourceProvider> {
        ResourceProviderImpl(androidApplication())
    }

    singleOf(::FilterManagerImpl) bind FilterManager::class
    singleOf(::LocationManagerImpl) bind LocationManager::class
}
