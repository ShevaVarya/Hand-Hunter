package ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity

data class DetailsVacancyEntity(
    val id: String,
    val name: String,
    val salary: SalaryEntity?,
    val employer: EmployerEntity,
    val area: AreaEntity,
    val experience: ExperienceEntity,
    @SerializedName("employment_form")
    val employmentForm: EmploymentFormEntity,
    val description: String,
    @SerializedName("key_skills")
    val keySkills: List<SkillEntity>
) {
    data class ExperienceEntity(
        val id: String,
        val name: String
    )

    data class EmploymentFormEntity(
        val id: String,
        val name: String
    )

    data class SkillEntity(
        val name: String
    )
}
