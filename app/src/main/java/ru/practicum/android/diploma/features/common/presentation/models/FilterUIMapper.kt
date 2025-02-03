package ru.practicum.android.diploma.features.common.presentation.models

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterMainData
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.RegionUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

fun CountryUI.toDomain(): FilterCountry {
    return FilterCountry(
        id = id,
        name = name,
    )
}

fun FilterCountry.toUI(): CountryUI {
    return CountryUI(
        id = id,
        name = name,
    )
}

fun IndustryUI.toDomain(): FilterIndustry {
    return FilterIndustry(
        id = id,
        name = name,
    )
}

fun FilterIndustry.toUI(): IndustryUI {
    return IndustryUI(
        id = id,
        name = name,
    )
}

fun RegionUI.toDomain(): FilterRegion {
    return FilterRegion(
        id = id,
        name = name,
        parentId = parentId,
    )
}

fun FilterRegion.toUI(): RegionUI {
    return RegionUI(
        id = id,
        name = name,
        parentId = parentId
    )
}

fun FilterUI.toDomain(): FilterMainData {
    return FilterMainData(
        country = country?.toDomain() ?: FilterCountry(id = "", name = ""),
        region = region?.toDomain() ?: FilterRegion(id = "", name = "", parentId = ""),
        industry = industry?.toDomain() ?: FilterIndustry(id = "", name = ""),
        salary = salary.toString(),
        isNeedToHideVacancyWithoutSalary = onlyWithSalary
    )
}

fun FilterMainData.toUI(): FilterUI {
    return FilterUI(
        country = country.toUI(),
        region = region.toUI(),
        industry = industry.toUI(),
        salary = salary.toIntOrNull(),
        onlyWithSalary = isNeedToHideVacancyWithoutSalary,
    )
}
