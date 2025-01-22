package ru.practicum.android.diploma.features.vacancy.domain.model

data class VacancyDetails(
    val id: String,
    val title: String,
    val salary: Salary?,
    val employer: Employer?,
    val location: String?,
    val experience: String?,
    val employmentType: String?,
    val description: String?,
    val keySkills: List<String>?
)

data class Salary(
    val from: Int?,
    val to: Int?,
    val currency: String?,
    val isGross: Boolean?
)

data class Employer(
    val name: String,
    val logoUrl: String?
)
