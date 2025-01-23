package ru.practicum.android.diploma.features.vacancy.data.dto

import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity.EmploymentFormEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity.ExperienceEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity.SkillEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.EmployerEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.SalaryEntity
import ru.practicum.android.diploma.features.vacancy.domain.model.Employer
import ru.practicum.android.diploma.features.vacancy.domain.model.Salary
import ru.practicum.android.diploma.features.vacancy.domain.model.VacancyDetails

fun DetailsVacancyEntity.toDomain(): VacancyDetails {
    return VacancyDetails(
        id = id,
        title = name,
        salary = salary?.toDomain() ?: Salary.stub,
        employer = employer.toDomain(),
        location = area.toDomain(),
        experience = experience.toDomain(),
        employmentType = employmentForm.toDomain(),
        description = description,
        keySkills = keySkills.map { it.toDomain() }
    )
}

fun SalaryEntity.toDomain(): Salary {
    return Salary(
        from = from ?: 0,
        to = to ?: 0,
        currency = currency ?: "",
        isGross = gross ?: false
    )
}

fun EmployerEntity.toDomain(): Employer {
    return Employer(
        logoUrl = logoUrls?.original,
        name = name
    )
}

fun AreaEntity.toDomain(): String {
    return name
}

fun ExperienceEntity.toDomain(): String {
    return name
}

fun EmploymentFormEntity.toDomain(): String {
    return name
}

fun SkillEntity.toDomain(): String {
    return name
}
