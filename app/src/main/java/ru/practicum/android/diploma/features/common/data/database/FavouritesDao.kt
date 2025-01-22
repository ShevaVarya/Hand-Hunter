package ru.practicum.android.diploma.features.common.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FavouritesDao {
    @Insert(entity = VacancyDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVacancyToFavourites(vacancy: VacancyDbEntity)

    @Insert(entity = KeySkillEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKeySkillsToFavourites(keySkills: KeySkillEntity)

    @Transaction
    suspend fun addToFavourites(vacancy: VacancyDbEntity, keySkills: KeySkillEntity) {
        addVacancyToFavourites(vacancy)
        addKeySkillsToFavourites(keySkills)
    }

    @Query("SELECT * FROM favourites_table")
    suspend fun getFavourites(): List<VacancyDbEntity>
}
