package ru.practicum.android.diploma.features.filters.presentation.model

import ru.practicum.android.diploma.features.filters.domain.model.Country
import ru.practicum.android.diploma.features.filters.domain.model.FilterRegion
import ru.practicum.android.diploma.features.filters.domain.model.Industry
import ru.practicum.android.diploma.features.filters.domain.model.Region
import ru.practicum.android.diploma.features.filters.presentation.model.ui.CountryUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.IndustryUI
import ru.practicum.android.diploma.features.filters.presentation.model.ui.RegionUI

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

fun RegionUI.fromUI(): FilterRegion {
    return FilterRegion(
        id = id,
        parentId = parentId,
        name = name
    )
}

fun Industry.toUI(): IndustryUI {
    return IndustryUI(
        id = id,
        name = name
    )
}

fun IndustryUI.fromUI(): Industry {
    return Industry(
        id = id,
        name = name
    )
}
