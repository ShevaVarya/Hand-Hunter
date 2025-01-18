package ru.practicum.android.diploma.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import ru.practicum.android.diploma.BuildConfig

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

//    раскоментить после создания интерфейса API и вставить вместо NetworkApi название созданного интерфейса

//    single<NetworkApi> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//            .create(NetworkApi::class.java)
//    }

}
