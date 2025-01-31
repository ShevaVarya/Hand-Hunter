package ru.practicum.android.diploma.features.common.domain.filter.model

data class FilterMainData(
    val country: FilterCountry,
    val region: FilterRegion,
    val industry: FilterIndustry,
    val salary: String,
    val isNeedToHideVacancyWithoutSalary: Boolean,
)
