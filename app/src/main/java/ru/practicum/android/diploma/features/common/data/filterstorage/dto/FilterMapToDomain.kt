package ru.practicum.android.diploma.features.common.data.filterstorage.dto

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FullLocationData

fun FilterMainDataEntity.toDomain(): FilterMainData {
    return FilterMainData(
        country = country,
        region = region,
        industry = industry,
        salary = salary,
        isNeedToHideVacancyWithoutSalary = isNeedToHideVacancyWithoutSalary
    )
}

fun FullLocationDataEntity.toDomain(): FullLocationData {
    return FullLocationData(
        country = country,
        region = region
    )
}
