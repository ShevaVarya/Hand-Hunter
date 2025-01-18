package ru.practicum.android.diploma.features.common.data.network.dto.industry

data class IndustryEntity(
    val id: String,
    val industries: List<IndustryEntity>?,
    val name: String
)
