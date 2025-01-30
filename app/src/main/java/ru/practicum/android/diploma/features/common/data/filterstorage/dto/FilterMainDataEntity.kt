package ru.practicum.android.diploma.features.common.data.filterstorage.dto

data class FilterMainDataEntity(
    val country: String,
    val region: String,
    val industry: String,
    val salary: String,
    val isNeedToHideVacancyWithoutSalary: Boolean,
)
