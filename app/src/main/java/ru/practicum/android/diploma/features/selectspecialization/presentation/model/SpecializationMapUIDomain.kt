package ru.practicum.android.diploma.features.selectspecialization.presentation.model

import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

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
