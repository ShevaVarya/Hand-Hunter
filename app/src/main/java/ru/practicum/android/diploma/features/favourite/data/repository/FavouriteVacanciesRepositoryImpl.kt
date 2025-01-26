package ru.practicum.android.diploma.features.favourite.data.repository

import ru.practicum.android.diploma.features.common.data.database.AppDatabase
import ru.practicum.android.diploma.features.common.data.database.KeySkillEntity
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.favourite.data.dto.toDb
import ru.practicum.android.diploma.features.favourite.domain.api.FavouriteVacanciesRepository

class FavouriteVacanciesRepositoryImpl(
    private val appDatabase: AppDatabase,
) : FavouriteVacanciesRepository {

    override suspend fun addToFavourites(vacancy: VacancyDetails) {
        val vacancyDb = vacancy.toDb()
        vacancy.keySkills.forEach { skill ->
            appDatabase.favouritesDao().addToFavourites(
                vacancyDb, createKeySkillEntity(vacancy.id, skill)
            )
        }
    }

    private fun createKeySkillEntity(vacancyId: String, skill: String): KeySkillEntity {
        return KeySkillEntity(
            id = 0,
            vacancyId = vacancyId,
            keySkill = skill,
        )
    }

}

