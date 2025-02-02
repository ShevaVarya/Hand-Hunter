package ru.practicum.android.diploma.features.selectlocation.data.dto

import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterCountryEntity
import ru.practicum.android.diploma.features.common.data.network.dto.area.CountryEntity
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

fun CountryEntity.toDomain(): Country {
    return Country(
        id = id,
        name = name
    )
}

fun Country.toFilterEntity(): FilterCountryEntity {
    return FilterCountryEntity(
        id = id,
        name = name
    )
}
