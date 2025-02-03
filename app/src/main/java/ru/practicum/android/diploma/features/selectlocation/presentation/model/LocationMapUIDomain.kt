package ru.practicum.android.diploma.features.selectlocation.presentation.model

import ru.practicum.android.diploma.features.selectlocation.domain.model.Country
import ru.practicum.android.diploma.features.selectlocation.domain.model.Region

fun Country.toUI(): CountryUI {
    return CountryUI(
        id = id,
        name = name
    )
}

fun CountryUI.fromUI(): Country {
    return Country(
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

fun RegionUI.fromUI(): Region {
    return Region(
        id = id,
        parentId = parentId,
        name = name
    )
}
