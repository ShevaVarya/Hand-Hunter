package ru.practicum.android.diploma.features.favourite.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository

class FavouriteVacanciesInteractorImpl(
    private val repository: FavouriteVacanciesRepository
) : FavouriteVacanciesInteractor {

    override fun getFavourites(): Flow<List<Vacancy>> {
        return repository.getFavourites().map { vacancies ->
            vacancies.reversed()
        }
    }

}
