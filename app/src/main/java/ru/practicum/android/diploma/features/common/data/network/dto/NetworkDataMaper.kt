package ru.practicum.android.diploma.features.common.data.network.dto

import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacancyEntity
import ru.practicum.android.diploma.features.search.domain.model.Vacancy


fun VacancyEntity.toDomain(): Vacancy {
    return Vacancy(
        id = id,
        name = name,
        city = area.name,
        employerName = employer.name,
        employerLogoUrl = employer.logoUrls?.original,
        salaryFrom = salary?.from,
        salaryTo = salary?.to,
        currencySymbol = salary?.currency
    )
}
