package ru.practicum.android.diploma.features.filters.domain.model

fun Industry.toFilterIndustry(): Industry {
    return Industry(
        id = id,
        name = name
    )
}
