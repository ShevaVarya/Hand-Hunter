package ru.practicum.android.diploma.features.filters.domain.model

data class FilterMainData(
    val country: Country?,
    val region: FilterRegion?,
    val industry: Industry?,
    val salary: String?,
    val isNeedToHideVacancyWithoutSalary: Boolean,
)
