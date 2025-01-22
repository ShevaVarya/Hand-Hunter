package ru.practicum.android.diploma.features.favourite.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.common.data.database.AppDatabase
import ru.practicum.android.diploma.features.common.data.database.VacancyDbEntity
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository
import ru.practicum.android.diploma.features.favourite.domain.model.Vacancy

class FavouriteVacanciesRepositoryImpl(
    private val appDatabase: AppDatabase,
) : FavouriteVacanciesRepository {

    override fun getFavourites(): Flow<List<Vacancy>> = flow {
        val vacancies = appDatabase.favouritesDao().getFavourites()
        emit(convertFromDbEntity(vacancies))
    }

    private fun convertFromDbEntity(vacanciesDbEntity: List<VacancyDbEntity>): List<Vacancy> {
        return vacanciesDbEntity.map { vacancyDbEntity ->
            Vacancy(
                vacancyId = vacancyDbEntity.vacancyId,
                title = vacancyDbEntity.title,
                salary = vacancyDbEntity.salary,
                employerName = vacancyDbEntity.employerName,
                employerLogoUrl = vacancyDbEntity.employerLogoUrl,
                location = vacancyDbEntity.location,
                experience = vacancyDbEntity.experience,
                employmentForm = vacancyDbEntity.employmentForm,
                description = vacancyDbEntity.description
            )
        }
    }

}
