package ru.practicum.android.diploma.features.favourite.domain.model

data class FavouriteVacancy(
    val vacancyId: String,
    val title: String,
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val currencySymbol: String?,
    val isGross: Boolean?,
    val employerName: String,
    val employerLogoUrl: String,
    val location: String,
    val experience: String,
    val employmentForm: String,
    val description: String
)
