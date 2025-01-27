package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.common.presentation.ResourceProviderImpl

val utilsModule = module {
    single<ResourceProvider> {
        ResourceProviderImpl(androidApplication())
    }
}
