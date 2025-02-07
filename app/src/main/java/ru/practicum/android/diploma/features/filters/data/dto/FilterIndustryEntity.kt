package ru.practicum.android.diploma.features.filters.data.dto

import ru.practicum.android.diploma.features.filters.domain.model.Industry

@Suppress("DetektRuleName")
data class FilterIndustryEntity(
    val id: String?,
    val name: String?
) {
    fun toDomain(): Industry? {
        return if (id.isNullOrEmpty() && name.isNullOrEmpty()) {
            null
        } else {
            Industry(id = id, name = name)
        }
    }
}
