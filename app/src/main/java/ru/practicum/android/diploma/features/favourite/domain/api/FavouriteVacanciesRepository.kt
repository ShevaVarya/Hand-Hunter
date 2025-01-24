package ru.practicum.android.diploma.features.favourite.domain.api

import kotlinx.coroutines.flow.Flow

interface FavouriteVacanciesRepository {

    fun getFavourites(): Flow<List<Vacancy>>

}
