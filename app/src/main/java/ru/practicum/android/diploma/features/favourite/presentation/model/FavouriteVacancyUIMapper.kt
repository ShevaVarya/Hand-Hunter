package ru.practicum.android.diploma.features.favourite.presentation.model

import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.vacancy.presentation.model.toUI

fun VacancyDetails.toUi(resourceProvider: ResourceProvider): FavouriteVacancyUI {
    return FavouriteVacancyUI(
        vacancyId = id,
        title = title,
        salary = salary.toUI(resourceProvider),
        employerLogoUrl = employer.logoUrl ?: "",
        employerName = employer.name,
        location = location
    )
}
