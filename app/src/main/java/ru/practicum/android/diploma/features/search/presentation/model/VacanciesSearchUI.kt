package ru.practicum.android.diploma.features.search.presentation.model

import ru.practicum.android.diploma.features.common.presentation.models.VacancySearchUI

data class VacanciesSearchUI(
    val items: List<VacancySearchUI>,
    val found: Int,
    val pages: Int,
    val page: Int,
    val perPage: Int
)
