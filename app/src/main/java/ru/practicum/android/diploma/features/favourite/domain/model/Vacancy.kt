package ru.practicum.android.diploma.features.favourite.domain.model

import androidx.room.ColumnInfo

//mock
data class Vacancy(
    val vacancyId: String,
    val title: String,
    val salary: String,
    val employerName: String,
    val employerLogoUrl: String,
    val location: String,
    val experience: String,
    val employmentForm: String,
    val description: String
)
