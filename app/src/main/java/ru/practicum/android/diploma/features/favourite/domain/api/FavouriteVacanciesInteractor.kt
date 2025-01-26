package ru.practicum.android.diploma.features.favourite.domain.api

import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails

interface FavouriteVacanciesInteractor {

    suspend fun addToFavourites(vacancy: VacancyDetails)

}
