package ru.practicum.android.diploma.features.vacancy.domain.model

data class VacancyDetails(
    val id: String,
    val title: String,
    val salary: Salary = Salary.stub,
    val employer: Employer = Employer.stub,
    val location: String = "",
    val experience: String = "",
    val employmentType: String = "",
    val description: String = "",
    val keySkills: List<String> = emptyList()
)

data class Salary(
    val from: Int = 0,
    val to: Int = 0,
    val currency: String = "",
    val isGross: Boolean = false
) {
    companion object {
        val stub = Salary()
    }
}

data class Employer(
    val name: String = "",
    val logoUrl: String? = null
) {
    companion object {
        val stub = Employer()
    }
}
