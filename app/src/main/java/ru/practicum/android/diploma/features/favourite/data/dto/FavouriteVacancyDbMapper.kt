package ru.practicum.android.diploma.features.favourite.data.dto

import ru.practicum.android.diploma.features.common.data.database.VacancyDbEntity
import ru.practicum.android.diploma.features.favourite.domain.model.FavouriteVacancy

fun VacancyDbEntity.toDomain(): FavouriteVacancy {
    return FavouriteVacancy(
        vacancyId = vacancyId,
        title = title,
        salaryFrom = salaryFrom,
        salaryTo = salaryTo,
        currencySymbol = currencySymbol,
        isGross = isGross,
        employerName = employerName,
        employerLogoUrl = employerLogoUrl,
        location = location,
        experience = experience,
        employmentForm = employmentForm,
        description = description
    )
}

fun FavouriteVacancy.toDb(): VacancyDbEntity {
    return VacancyDbEntity(
        vacancyId = vacancyId,
        title = title,
        salaryFrom = salaryFrom,
        salaryTo = salaryTo,
        currencySymbol = currencySymbol,
        isGross = isGross,
        employerName = employerName,
        employerLogoUrl = employerLogoUrl,
        location = location,
        experience = experience,
        employmentForm = employmentForm,
        description = description
    )
}
