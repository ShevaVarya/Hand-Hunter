package ru.practicum.android.diploma.features.favourite.presentation.model

data class VacancyUI(
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
