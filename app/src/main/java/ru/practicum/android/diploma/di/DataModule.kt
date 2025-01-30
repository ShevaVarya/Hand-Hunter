package ru.practicum.android.diploma.di

import androidx.room.Room
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.features.common.data.database.AppDatabase
import ru.practicum.android.diploma.features.common.data.database.FavouritesDao
import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorageImpl
import ru.practicum.android.diploma.features.common.data.network.api.HHApi
import ru.practicum.android.diploma.features.common.data.network.service.NetworkClient
import ru.practicum.android.diploma.features.common.data.network.service.NetworkClientImpl
import ru.practicum.android.diploma.utils.NetworkChecker

private const val BASE_URL = "https://api.hh.ru/"
private const val BASE_EMAIL = "Kazesteam@yandex.ru"
private const val APP_NAME = "Hand Hunter"

val dataModule = module {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val authInterceptor = Interceptor { chain ->
        val request = chain
            .request()
            .newBuilder()
            .addHeader(name = "Authorization", value = "Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
            .addHeader(name = "HH-User-Agent", value = "$APP_NAME ($BASE_EMAIL)")
            .build()

        chain.proceed(request)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()

    single<HHApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HHApi::class.java)
    }

    singleOf(::NetworkClientImpl) bind NetworkClient::class

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    single<FavouritesDao> {
        val db: AppDatabase = get()
        db.favouritesDao()
    }

    single<NetworkChecker> { NetworkChecker(get()) }

    singleOf(::FilterStorageImpl) bind FilterStorage::class
}
