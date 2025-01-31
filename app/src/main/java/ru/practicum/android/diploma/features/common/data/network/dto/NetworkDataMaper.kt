package ru.practicum.android.diploma.features.common.data.network.dto

import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacancyEntity
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.features.vacancy.data.dto.mapCurrencyToDomain

fun VacancyEntity.toDomain(): Vacancy {
    return Vacancy(
        id = id,
        name = name,
        city = area.name,
        employerName = employer.name,
        employerLogoUrl = employer.logoUrls?.original ?: "",
        salaryFrom = salary?.from ?: 0,
        salaryTo = salary?.to ?: 0,
        currencySymbol = mapCurrencyToDomain(salary?.currency).symbol
    )
}

fun VacanciesEntity.toDomain(): Vacancies {
    return Vacancies(
        items = items.map { it.toDomain() },
        found = found,
        pages = pages,
        page = page,
        perPage = perPage,
    )
}
