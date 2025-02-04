package ru.practicum.android.diploma.features.filters.domain.model

fun Industry.toFilterIndustry(): FilterIndustry {
    return FilterIndustry(
        id = this.id,
        name = this.name
    )
}
