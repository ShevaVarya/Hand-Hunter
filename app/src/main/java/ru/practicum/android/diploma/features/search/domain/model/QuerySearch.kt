package ru.practicum.android.diploma.features.search.domain.model

data class QuerySearch(
    val text: String?,
    val page: Int = 0,
    val perPage: Int = 20,
    val params: Map<String, String> = mapOf(),
)
