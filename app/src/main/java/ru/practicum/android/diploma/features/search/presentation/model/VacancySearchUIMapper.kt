package ru.practicum.android.diploma.features.search.presentation.model

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import java.text.NumberFormat
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
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())

    val salaryFrom =
        vacancy.salaryFrom.takeIf { it != 0 }?.let { "%s %s".format(numberFormat.format(it), vacancy.currencySymbol) }
            ?: EMPTY_STRING
    val salaryTo =
        vacancy.salaryTo.takeIf { it != 0 }?.let { "%s %s".format(numberFormat.format(it), vacancy.currencySymbol) }
            ?: EMPTY_STRING

    return when {
        salaryFrom.isNotEmpty() && salaryTo.isNotEmpty() ->
            "%s %s %s %s".format(
                Locale.getDefault(),
                resourceProvider.getString(R.string.salary_from),
                salaryFrom,
                resourceProvider.getString(R.string.salary_to),
                salaryTo
            )

        salaryFrom.isNotEmpty() ->
            "%s %s".format(Locale.getDefault(), resourceProvider.getString(R.string.salary_from), salaryFrom)

        salaryTo.isNotEmpty() ->
            "%s %s".format(Locale.getDefault(), resourceProvider.getString(R.string.salary_to), salaryTo)

        else -> resourceProvider.getString(R.string.no_salary)
    }
}
