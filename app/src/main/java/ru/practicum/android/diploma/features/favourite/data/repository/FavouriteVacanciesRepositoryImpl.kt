package ru.practicum.android.diploma.features.favourite.data.repository

import ru.practicum.android.diploma.features.common.data.database.AppDatabase
import ru.practicum.android.diploma.features.common.data.database.KeySkillEntity
import ru.practicum.android.diploma.features.favourite.data.dto.toDb
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository
import ru.practicum.android.diploma.features.favourite.domain.model.FavouriteVacancy

class FavouriteVacanciesRepositoryImpl(
    private val appDatabase: AppDatabase,
) : FavouriteVacanciesRepository {

    override suspend fun addToFavourites(vacancy: FavouriteVacancy, keySkills: String) {
        appDatabase.favouritesDao().addToFavourites(
            vacancy.toDb(),
            createKeySkillEntity(vacancy, keySkills)
        )
    }

    private fun createKeySkillEntity(vacancy: FavouriteVacancy, keySkills: String): KeySkillEntity {
        return KeySkillEntity(
            id = 0,
            vacancyId = vacancy.vacancyId,
            keySkill = keySkills,
        )
    }

}

