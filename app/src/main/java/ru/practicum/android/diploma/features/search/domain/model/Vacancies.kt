package ru.practicum.android.diploma.features.search.domain.model

data class Vacancies(
    val items: List<Vacancy>,
    val found: Int,
    val pages: Int,
    val page: Int,
    val perPage: Int
)
