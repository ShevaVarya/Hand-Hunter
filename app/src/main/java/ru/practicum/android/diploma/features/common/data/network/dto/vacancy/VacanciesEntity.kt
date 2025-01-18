package ru.practicum.android.diploma.features.common.data.network.dto.vacancy

import com.google.gson.annotations.SerializedName

data class VacanciesEntity(
    val items: List<VacancyEntity>,
    val pages: Int,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int
)
