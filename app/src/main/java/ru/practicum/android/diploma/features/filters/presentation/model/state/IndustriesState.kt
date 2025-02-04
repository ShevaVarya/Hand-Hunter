package ru.practicum.android.diploma.features.filters.presentation.model.state

import ru.practicum.android.diploma.features.filters.presentation.model.ui.IndustryUI

sealed interface IndustriesState {
    data object Loading : IndustriesState
    data object Error : IndustriesState
    data class Content(val industries: List<IndustryUI>) : IndustriesState
}
