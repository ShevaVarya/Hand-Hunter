package ru.practicum.android.diploma.features.favourite.domain.interactor

import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository
import ru.practicum.android.diploma.features.favourite.domain.model.FavouriteVacancy

class FavouriteVacanciesInteractorImpl(
    private val repository: FavouriteVacanciesRepository
) : FavouriteVacanciesInteractor {

    override suspend fun addToFavourites(vacancy: FavouriteVacancy, keySkills: String) {
        repository.addToFavourites(vacancy, keySkills)
    }

}
