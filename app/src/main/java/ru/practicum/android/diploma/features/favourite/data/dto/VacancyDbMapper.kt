package ru.practicum.android.diploma.features.favourite.data.dto

import ru.practicum.android.diploma.features.common.data.database.VacancyDbEntity
import ru.practicum.android.diploma.features.favourite.domain.model.FavouriteVacancy
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

fun VacancyDbEntity.toDomain(): FavouriteVacancy {
    return FavouriteVacancy(
        vacancyId = vacancyId ,
        title = title,
        salary = salary,
        employerName = employerName,
        employerLogoUrl = employerLogoUrl,
        location = location,
        experience = experience,
        employmentForm = employmentForm,
        description = description
    )
}
