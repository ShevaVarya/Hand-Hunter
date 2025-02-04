package ru.practicum.android.diploma.features.filters.presentation.model.state

import ru.practicum.android.diploma.features.filters.presentation.model.ui.WorkplaceLocationUI

sealed interface WorkplaceLocationState {
    object Init : WorkplaceLocationState
    data class Content(val location: WorkplaceLocationUI) : WorkplaceLocationState
}
