package ru.practicum.android.diploma.features.vacancy.presentation.model

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.domain.model.Salary
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider

private const val EMPTY_STRING = ""

fun VacancyDetails.toUI(resourceProvider: ResourceProvider): VacancyInfoUI {
    return VacancyInfoUI(
        title = title,
        salary = salary.toUI(resourceProvider),
        employerLogoUrl = employer.logoUrl ?: "",
        employerName = employer.name,
        location = location,
        experience = experience,
        employmentForm = employmentType,
        description = description,
        keySkills = mapKeySkillsListToString(keySkills)
    )
}

fun mapKeySkillsListToString(keySkills: List<String>): String {
    val result = StringBuilder()
    keySkills.forEach { result.append("$it\n") }
    return result.toString()
}

fun Salary.toUI(resourceProvider: ResourceProvider): String {
    return if (this == Salary.stub) {
        resourceProvider.getString(R.string.vacancy_info_no_salary)
    } else {
        val from = getFromValueForUI(resourceProvider, this.from)
        val to = getToValueForUI(resourceProvider, this.to)
        val grossInfo = getGrossInfoForUI(resourceProvider, isGross)

        return String.format(
            resourceProvider.getString(R.string.vacancy_info_salary_total_string),
            from,
            to,
            currency.symbol,
            grossInfo
        )
    }
}

private fun getFromValueForUI(resourceProvider: ResourceProvider, from: Int): String {
    return if (from == 0) {
        EMPTY_STRING
    } else {
        String.format(resourceProvider.getString(R.string.vacancy_info_salary_from), from)
    }
}

private fun getToValueForUI(resourceProvider: ResourceProvider, to: Int): String {
    return if (to == 0) {
        EMPTY_STRING
    } else {
        String.format(resourceProvider.getString(R.string.vacancy_info_salary_to), to)
    }
}

private fun getGrossInfoForUI(resourceProvider: ResourceProvider, isGross: Boolean?): String {
    return if (isGross == null) {
        EMPTY_STRING
    } else {
        resourceProvider.getString(
            if (isGross) {
                R.string.vacancy_info_gross_info_true
            } else {
                R.string.vacancy_info_gross_info_false
            }
        )
    }
}

