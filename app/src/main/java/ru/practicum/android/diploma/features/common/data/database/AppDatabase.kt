package ru.practicum.android.diploma.features.common.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        VacancyDbEntity::class,
        KeySkillEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}
