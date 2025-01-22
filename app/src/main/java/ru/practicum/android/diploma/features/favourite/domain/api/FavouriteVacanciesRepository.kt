package ru.practicum.android.diploma.features.favourite.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.favourite.domain.model.Vacancy

interface FavouriteVacanciesRepository {

    fun getFavourites(): Flow<List<Vacancy>>

}
