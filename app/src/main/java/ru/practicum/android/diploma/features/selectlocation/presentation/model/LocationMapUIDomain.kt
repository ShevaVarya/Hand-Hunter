package ru.practicum.android.diploma.features.selectlocation.presentation.model

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterCountry
import ru.practicum.android.diploma.features.common.domain.filter.model.FilterRegion
import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

fun Country.toUI(): CountryUI {
    return CountryUI(
        id = id,
        name = name
    )
}

fun CountryUI.fromUI(): FilterCountry {
    return FilterCountry(
        id = id,
        name = name
    )
}

fun Region.toUI(): RegionUI {
    return RegionUI(
        id = id,
        parentId = parentId,
        name = name
    )
}

fun RegionUI.fromUI(): FilterRegion {
    return FilterRegion(
        id = id,
        parentId = parentId,
        name = name
    )
}
