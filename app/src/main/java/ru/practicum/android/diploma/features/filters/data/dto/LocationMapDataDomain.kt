package ru.practicum.android.diploma.features.filters.data.dto

import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.area.CountryEntity
import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Region

fun CountryEntity.toDomain(): Country {
    return Country(
        id = id,
        name = name
    )
}

fun AreaEntity.toDomain(): Region {
    return Region(
        id = id,
        parentId = parentId ?: "",
        name = name,
        areas = areas?.map { it.toDomain() } ?: listOf()
    )
}
