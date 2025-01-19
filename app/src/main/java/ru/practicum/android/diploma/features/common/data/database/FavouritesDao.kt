package ru.practicum.android.diploma.features.common.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction

@Dao
interface FavouritesDao {
    @Insert(entity = VacancyDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVacancyToFavourites(vacancy: VacancyDbEntity)

    @Insert(entity = KeySkillsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKeySkillsToFavourites(keySkills: KeySkillsEntity)

    @Transaction
    suspend fun addToFavourites(vacancy: VacancyDbEntity, keySkills: KeySkillsEntity) {
        addVacancyToFavourites(vacancy)
        addKeySkillsToFavourites(keySkills)
    }
}
