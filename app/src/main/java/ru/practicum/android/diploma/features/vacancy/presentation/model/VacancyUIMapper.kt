package ru.practicum.android.diploma.features.vacancy.presentation.model

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import ru.practicum.android.diploma.features.vacancy.domain.model.Salary
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

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
        val from = if (this.from == 0) {
            EMPTY_STRING
        } else {
            resourceProvider.getString(R.string.salary_from)+ " $from"
        }

        val to = if (this.to == 0) {
            EMPTY_STRING
        } else {
            resourceProvider.getString(R.string.salary_to)+ " $to"
        }

        val grossInfo = if (isGross == null) {
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
        return StringBuilder().append("$from ").append("$to ").append(currency.symbol).append(" $grossInfo").toString()
    }
}

