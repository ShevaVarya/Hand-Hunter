package ru.practicum.android.diploma.utils

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import java.text.NumberFormat
import java.util.Locale

private const val EMPTY_STRING = ""
private const val SALARY_FORMAT = "%s %s"
private const val DOUBLE_SALARY_FORMAT = "%s %s %s %s"

fun getProfession(vacancyName: String, city: String): String {
    return String.format(Locale.getDefault(), SALARY_FORMAT, vacancyName, city)
}

fun getFormatSalary(
    vacancySalaryFrom: Int,
    vacancySalaryTo: Int,
    vacancyCurrencySymbol: String,
    resourceProvider: ResourceProvider
): String {
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())

    val salaryFrom =
        vacancySalaryFrom.takeIf { it != 0 }
            ?.let { SALARY_FORMAT.format(numberFormat.format(it), vacancyCurrencySymbol) }
            ?: EMPTY_STRING
    val salaryTo =
        vacancySalaryTo.takeIf { it != 0 }
            ?.let { SALARY_FORMAT.format(numberFormat.format(it), vacancyCurrencySymbol) }
            ?: EMPTY_STRING

    return when {
        salaryFrom.isNotEmpty() && salaryTo.isNotEmpty() ->
            DOUBLE_SALARY_FORMAT.format(
                Locale.getDefault(),
                resourceProvider.getString(R.string.salary_from),
                salaryFrom,
                resourceProvider.getString(R.string.salary_to),
                salaryTo
            )

        salaryFrom.isNotEmpty() ->
            SALARY_FORMAT.format(Locale.getDefault(), resourceProvider.getString(R.string.salary_from), salaryFrom)

        salaryTo.isNotEmpty() ->
            SALARY_FORMAT.format(Locale.getDefault(), resourceProvider.getString(R.string.salary_to), salaryTo)

        else -> resourceProvider.getString(R.string.no_salary)
    }
}
