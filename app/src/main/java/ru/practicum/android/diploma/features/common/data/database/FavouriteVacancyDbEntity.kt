package ru.practicum.android.diploma.features.common.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteVacancyDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    val title: String,
    val location: String,
    val employer: String,
    val salary: String
)
