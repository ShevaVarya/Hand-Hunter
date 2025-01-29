package ru.practicum.android.diploma.features.vacancy.presentation.model

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.common.domain.model.Address
import ru.practicum.android.diploma.features.common.domain.model.Salary
import ru.practicum.android.diploma.features.common.domain.model.VacancyDetails
import ru.practicum.android.diploma.features.common.presentation.ResourceProvider
import java.text.NumberFormat
import java.util.Locale

private const val EMPTY_STRING = ""
private const val SALARY_STUB_VALUE = 0

fun VacancyDetails.toUI(resourceProvider: ResourceProvider): VacancyInfoUI {
    return VacancyInfoUI(
        title = title,
        salary = salary.toUI(resourceProvider),
        employerLogoUrl = employer.logoUrl ?: "",
        employerName = employer.name,
        location = getAddressOrLocation(this.address, this.location),
        experience = experience,
        employmentForm = employmentType,
        description = description,
        keySkills = mapKeySkillsListToString(keySkills),
        isFavourite = isFavourite
    )
}

fun mapKeySkillsListToString(keySkills: List<String>): String {
    val result = StringBuilder()
    keySkills.forEach { result.append("$it\n") }
    return result.toString()
}

fun Salary.toUI(resourceProvider: ResourceProvider): String {
    return if (isStub(this)) {
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

private fun isStub(salary: Salary): Boolean {
    return salary.from == SALARY_STUB_VALUE && salary.to == SALARY_STUB_VALUE
}

private fun getFromValueForUI(resourceProvider: ResourceProvider, from: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())
    return if (from == 0) {
        EMPTY_STRING
    } else {
        val formattedString = numberFormat.format(from)
        String.format(resourceProvider.getString(R.string.vacancy_info_salary_from), formattedString)
    }
}

private fun getToValueForUI(resourceProvider: ResourceProvider, to: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())
    return if (to == 0) {
        EMPTY_STRING
    } else {
        val formattedString = numberFormat.format(to)
        String.format(resourceProvider.getString(R.string.vacancy_info_salary_to), formattedString)
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

private fun getAddressOrLocation(address: Address?, location: String): String {
    return if (address == null || isAddressFieldsNull(address)) {
        location
    } else {
        createAddressText(address)
    }
}

private fun isAddressFieldsNull(address: Address): Boolean {
    return address.city == null && address.street == null && address.building == null
}

private fun createAddressText(address: Address): String {
    val result = StringBuilder().append(address.city ?: "")
    if (address.street != null) result.append(" ${address.street}")
    if (address.building != null) result.append(" ${address.building}")

    return result.toString()
}
