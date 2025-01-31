package ru.practicum.android.diploma.features.selectworkplace.presentation.model

data class CityUI(
    override val id: String,
    val parentId: String,
    override val name: String
) : Regionable()
