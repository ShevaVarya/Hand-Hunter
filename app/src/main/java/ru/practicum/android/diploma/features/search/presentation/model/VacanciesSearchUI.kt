package ru.practicum.android.diploma.features.search.presentation.model

data class VacanciesSearchUI(
    val items: List<VacancySearchUI>,
    val found: Int,
    val pages: Int,
    val page: Int,
    val perPage: Int
)
