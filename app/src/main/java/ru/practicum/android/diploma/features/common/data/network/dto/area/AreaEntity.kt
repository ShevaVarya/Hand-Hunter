package ru.practicum.android.diploma.features.common.data.network.dto.area

import com.google.gson.annotations.SerializedName

data class AreaEntity(
    val id: String,
    val name: String,
    @SerializedName("parent_id")
    val parentId: String?,
    val areas: List<AreaEntity>?
)
