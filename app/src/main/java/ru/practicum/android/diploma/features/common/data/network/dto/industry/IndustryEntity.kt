package ru.practicum.android.diploma.features.common.data.network.dto.industry

/**
 * Сущность для получения списка отраслей
 * @param id - идентификатор отрасли
 * @param industries - список дочерних отраслей (если у отрасли их нет - значение null)
 * @param name - название отрасли
 */
data class IndustryEntity(
    val id: String,
    val industries: List<IndustryEntity>?,
    val name: String
)
