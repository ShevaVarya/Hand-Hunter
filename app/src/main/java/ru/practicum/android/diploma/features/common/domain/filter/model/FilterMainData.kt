package ru.practicum.android.diploma.features.common.domain.filter.model

data class FilterMainData(
    val country: String,
    val region: String,
    val industry: String,
    val salary: String,
    val isNeedToHideVacancyWithoutSalary: Boolean,
)
