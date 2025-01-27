package ru.practicum.android.diploma.features.search.presentation.model

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import java.util.Locale

private const val EMPTY_STRING = ""

fun Vacancy.toUI(resourceProvider: ResourceProvider): VacancySearchUI {
    return VacancySearchUI(
        id = id,
        formatedProfession = getProfession(name, city),
        employerName = employerName,
        employerLogoUrl = employerLogoUrl,
        formatedSalary = getFormatSalary(this, resourceProvider),
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

private fun getProfession(vacancyName: String, city: String): String {
    return String.format(Locale.getDefault(), "%s, %s", vacancyName, city)
}

private fun getFormatSalary(vacancy: Vacancy, resourceProvider: ResourceProvider): String {
    val salaryFrom = vacancy.salaryFrom
    val salaryTo = vacancy.salaryTo

    val from = if (salaryFrom != 0) String.format(
        Locale.getDefault(),
        "%d %s",
        salaryFrom,
        vacancy.currencySymbol
    ) else EMPTY_STRING

    val to = if (salaryTo != 0) String.format(
        Locale.getDefault(),
        "%d %s",
        salaryTo,
        vacancy.currencySymbol
    ) else EMPTY_STRING

    return when {
        salaryFrom != 0 && salaryTo != 0 -> {
            String.format(
                Locale.getDefault(), "%s %s %s %s",
                resourceProvider.getString(R.string.salary_from),
                from,
                resourceProvider.getString(R.string.salary_to),
                to
            )
        }

        salaryFrom != 0 && salaryTo == 0 -> {
            String.format(
                Locale.getDefault(), "%s %s",
                resourceProvider.getString(R.string.salary_from),
                from
            )
        }

        salaryFrom == 0 && salaryTo != 0 -> {
            String.format(
                Locale.getDefault(), "%s %s",
                resourceProvider.getString(R.string.salary_to),
                to
            )
        }

        else -> resourceProvider.getString(R.string.no_salary)
    }
}
