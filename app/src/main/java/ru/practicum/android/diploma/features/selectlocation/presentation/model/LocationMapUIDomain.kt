package ru.practicum.android.diploma.features.selectlocation.presentation.model

import ru.practicum.android.diploma.features.selectlocation.domain.model.Country

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
