package ru.practicum.android.diploma.features.common.data.filterstorage.dto

data class FilterCountryEntity(
    val id: String,
    val name: String
) {
    companion object {
        fun default() = FilterCountryEntity(id = "", name = "")
    }
}
