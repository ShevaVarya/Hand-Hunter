package ru.practicum.android.diploma.features.common.data.network.dto.vacancy

import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.EmployerEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.SalaryEntity

data class VacancyEntity(
    val id: String,
    val name: String,
    val area: AreaEntity,
    val employer: EmployerEntity,
    val salary: SalaryEntity?
)
