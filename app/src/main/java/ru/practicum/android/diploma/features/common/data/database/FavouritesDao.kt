package ru.practicum.android.diploma.features.common.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FavouritesDao {
    @Insert(entity = FavouriteVacancyDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourites(vacancy: FavouriteVacancyDbEntity)
}
