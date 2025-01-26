package ru.practicum.android.diploma.features.favourite.data.dto

import ru.practicum.android.diploma.features.common.data.database.VacancyDbEntity
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails

fun VacancyDetails.toDb(): VacancyDbEntity {
    return VacancyDbEntity(
        vacancyId = id,
        title = title,
        salaryFrom = salary.from,
        salaryTo = salary.to,
        currencySymbol = salary.currency.symbol,
        isGross = salary.isGross,
        employerName = employer.name,
        employerLogoUrl = employer.logoUrl,
        location = location,
        experience = experience,
        employmentForm = employmentType,
        description = description
    )
}
