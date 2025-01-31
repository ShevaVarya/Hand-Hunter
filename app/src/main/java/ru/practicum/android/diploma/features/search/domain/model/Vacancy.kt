package ru.practicum.android.diploma.features.search.domain.model

data class Vacancy(
    val id: String,
    val name: String,
    val city: String,
    val employerName: String,
    val employerLogoUrl: String,
    val salaryFrom: Int,
    val salaryTo: Int,
    val currencySymbol: String,
)
