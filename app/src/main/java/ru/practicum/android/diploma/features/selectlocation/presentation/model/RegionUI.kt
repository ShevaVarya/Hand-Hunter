package ru.practicum.android.diploma.features.selectlocation.presentation.model

data class RegionUI(
    override val id: String,
    val parentId: String,
    override val name: String
) : Regionable
