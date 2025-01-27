package ru.practicum.android.diploma.features.favourite.presentation.model

import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.search.presentation.model.VacancySearchUI
import ru.practicum.android.diploma.features.search.presentation.model.getProfession
import ru.practicum.android.diploma.features.vacancy.presentation.model.toUI

fun VacancyDetails.toUi(resourceProvider: ResourceProvider): VacancySearchUI {
    return VacancySearchUI(
        id = id,
        formatedProfession = getProfession(title, location),
        formatedSalary = salary.toUI(resourceProvider),
        employerLogoUrl = employer.logoUrl ?: "",
        employerName = employer.name,
    )
}
