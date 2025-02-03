package ru.practicum.android.diploma.features.selectlocation.data.dto

import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.area.CountryEntity
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

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
        name = name
    )
}

fun mapRegionsToDomain(list: List<AreaEntity>, newList: MutableList<Region>): List<Region> {
    list.forEach {
        if (it.areas.isNullOrEmpty()) {
            newList.add(it.toDomain())
        } else {
            mapRegionsToDomain(it.areas, newList)
        }
    }
    return newList
}
