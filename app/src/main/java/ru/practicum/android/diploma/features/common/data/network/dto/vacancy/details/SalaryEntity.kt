package ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details

data class SalaryEntity(
    val currency: CurrencyEntity?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
) {
    data class CurrencyEntity(
        val abbr: String,
        val code: String,
        val rate: Int
    )
}
