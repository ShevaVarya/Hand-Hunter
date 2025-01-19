package ru.practicum.android.diploma.features.common.data.network.dto.vacancy

import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.EmployerEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.SalaryEntity

/**
 * Сущность для отображения списка вакансий
 * @param id - идентификатор вакансии
 * @param name - название вакансии
 * @param area - область
 * @param employer - информация о работодателе (логотип, название организации)
 * @param salary - информация о заработной плате (вилка или же только верхняя/нижняя граница, если в вакансии не указана з/п - значение null)
 */
data class VacancyEntity(
    val id: String,
    val name: String,
    val area: AreaEntity,
    val employer: EmployerEntity,
    val salary: SalaryEntity?
)
