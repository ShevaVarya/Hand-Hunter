package ru.practicum.android.diploma.features.filters.data.dto

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.Industry
import ru.practicum.android.diploma.features.filters.domain.model.FilterMainData
import ru.practicum.android.diploma.features.filters.domain.model.FilterRegion
import ru.practicum.android.diploma.features.filters.domain.model.FullLocationData

fun FilterMainDataEntity.toDomain(): FilterMainData {
    return FilterMainData(
        country = country.toDomain(),
        region = region.toDomain(),
        industry = industry.toDomain(),
        salary = salary,
        isNeedToHideVacancyWithoutSalary = isNeedToHideVacancyWithoutSalary
    )
}

fun FullLocationDataEntity.toDomain(): FullLocationData {
    return FullLocationData(
        country = country.toDomain(),
        region = region.toDomain()
    )
}

fun Country.toEntity(): FilterCountryEntity {
    return FilterCountryEntity(
        id = id,
        name = name
    )
}

fun FilterRegion.toEntity(): FilterRegionEntity {
    return FilterRegionEntity(
        id = id,
        name = name,
        parentId = parentId
    )
}

fun Industry.toEntity(): FilterIndustryEntity {
    return FilterIndustryEntity(
        id = id,
        name = name
    )
}

private fun FilterCountryEntity.toDomain(): Country {
    return Country(
        id = id,
        name = name
    )
}

private fun FilterRegionEntity.toDomain(): FilterRegion {
    return FilterRegion(
        id = id,
        name = name,
        parentId = parentId
    )
}

private fun FilterIndustryEntity.toDomain(): Industry {
    return Industry(
        id = id,
        name = name
    )
}
