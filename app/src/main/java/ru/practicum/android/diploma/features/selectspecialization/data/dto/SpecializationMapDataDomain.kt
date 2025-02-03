package ru.practicum.android.diploma.features.selectspecialization.data.dto

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterIndustryEntity
import ru.practicum.android.diploma.features.common.data.network.dto.industry.IndustryEntity
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

fun IndustryEntity.toDomain(): Industry {
    return Industry(
        id = id,
        name = name
    )
}

fun Industry.toFilterEntity(): FilterIndustryEntity {
    return FilterIndustryEntity(
        id = id,
        name = name
    )
}
