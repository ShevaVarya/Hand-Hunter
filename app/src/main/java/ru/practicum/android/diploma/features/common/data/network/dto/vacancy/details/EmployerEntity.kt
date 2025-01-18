package ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details

import com.google.gson.annotations.SerializedName

data class EmployerEntity(
    @SerializedName("logo_urls")
    val logoUrls: LogoUrlsEntity?,
    val name: String,
) {
    data class LogoUrlsEntity(
        val original: String
    )
}
