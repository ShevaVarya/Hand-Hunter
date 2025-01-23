package ru.practicum.android.diploma.features.common.data.network.dto.area

import com.google.gson.annotations.SerializedName

/**
 * Сущность для получения всех областей (от стран до городов)
 * @param id - идентификатор области
 * @param name - название области
 * @param parentId - идентификатор родительской области, к которой принадлежит данная область (у стран значение null)
 * @param areas - список дочерних областей (у городов значение null)
 */
data class AreaEntity(
    val id: String,
    val name: String,
    @SerializedName("parent_id")
    val parentId: String?,
    val areas: List<AreaEntity>?
)
