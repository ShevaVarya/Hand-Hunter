package ru.practicum.android.diploma.features.selectspecialization.data.dto

import ru.practicum.android.diploma.features.common.data.network.dto.industry.IndustryEntity
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

fun IndustryEntity.toDomain(): Industry {
    return Industry(
        id = id,
        name = name
    )
}

