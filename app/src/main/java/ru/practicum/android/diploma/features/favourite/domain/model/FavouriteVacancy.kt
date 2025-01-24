package ru.practicum.android.diploma.features.favourite.domain.model

data class FavouriteVacancy(
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
