package ru.practicum.android.diploma.features.common.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")
data class VacancyDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    val title: String,
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val currencySymbol: String?,
    val isGross: Boolean?,
    @ColumnInfo(name = "employer_name")
    val employerName: String,
    val employerLogoUrl: String,
    val location: String,
    val experience: String,
    @ColumnInfo(name = "employment_form")
    val employmentForm: String,
    val description: String
)
