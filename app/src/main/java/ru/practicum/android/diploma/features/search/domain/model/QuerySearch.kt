package ru.practicum.android.diploma.features.search.domain.model

data class QuerySearch(
    val text: String?,
    val page: Int = DEFAULT_PAGE,
    val perPage: Int = DEFAULT_PER_PAGE,
    val params: Map<String, String> = mapOf(),
) {
    companion object {
        const val DEFAULT_PAGE = 0
        const val DEFAULT_PER_PAGE = 20
    }
}
