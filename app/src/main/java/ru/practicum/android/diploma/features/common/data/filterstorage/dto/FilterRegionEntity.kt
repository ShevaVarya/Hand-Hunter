package ru.practicum.android.diploma.features.common.data.filterstorage.dto

data class FilterRegionEntity(
    val id: String,
    val name: String,
    val parentId: String
) {
    companion object {
        fun default() = FilterRegionEntity(id = "", name = "", parentId = "")
    }
}
