package ru.practicum.android.diploma.features.selectspecialization.presentation.model

sealed interface IndustriesState {
    data object Loading : IndustriesState
    data object Error : IndustriesState
    data class Content(val industries: List<Industries>) : IndustriesState
}
