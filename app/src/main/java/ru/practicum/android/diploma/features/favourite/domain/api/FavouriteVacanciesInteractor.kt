package ru.practicum.android.diploma.features.favourite.domain.api

import ru.practicum.android.diploma.features.favourite.domain.model.FavouriteVacancy

interface FavouriteVacanciesInteractor {

    suspend fun addToFavourites(vacancy: FavouriteVacancy, keySkills: String)

}
