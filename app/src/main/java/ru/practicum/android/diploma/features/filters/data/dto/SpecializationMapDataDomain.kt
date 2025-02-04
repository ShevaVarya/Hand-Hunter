package ru.practicum.android.diploma.features.filters.data.dto

import ru.practicum.android.diploma.features.common.data.network.dto.industry.IndustryEntity
import ru.practicum.android.diploma.features.filters.domain.model.Industry

fun IndustryEntity.toDomain(): Industry {
    return Industry(
        id = id,
        name = name
    )
}

