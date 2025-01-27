package ru.practicum.android.diploma.features.search.presentation.model

import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.utils.getFormatSalary
import ru.practicum.android.diploma.utils.getProfession

fun Vacancy.toUI(resourceProvider: ResourceProvider): VacancySearchUI {
    return VacancySearchUI(
        id = id,
        formatedProfession = getProfession(name, city),
        employerName = employerName,
        employerLogoUrl = employerLogoUrl,
        formatedSalary = getFormatSalary(this.salaryFrom, this.salaryTo, this.currencySymbol, resourceProvider),
    )
}

fun Vacancies.toUI(resourceProvider: ResourceProvider): VacanciesSearchUI {
    return VacanciesSearchUI(
        items = items.map { it.toUI(resourceProvider) },
        found = found,
        pages = pages,
        page = page,
        perPage = perPage,
    )
}
