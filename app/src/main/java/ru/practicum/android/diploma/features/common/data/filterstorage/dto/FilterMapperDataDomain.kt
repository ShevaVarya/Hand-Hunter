package ru.practicum.android.diploma.features.common.data.filterstorage.dto

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

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

fun FilterCountry.toEntity(): FilterCountryEntity {
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

fun FilterIndustry.toEntity(): FilterIndustryEntity {
    return FilterIndustryEntity(
        id = id,
        name = name
    )
}

private fun FilterCountryEntity.toDomain(): FilterCountry {
    return FilterCountry(
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

private fun FilterIndustryEntity.toDomain(): FilterIndustry {
    return FilterIndustry(
        id = id,
        name = name
    )
}
