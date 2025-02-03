package ru.practicum.android.diploma.features.selectworkplace.presentation.model

sealed interface WorkplaceLocationState {
    object Loading : WorkplaceLocationState
    object Init : WorkplaceLocationState
    data class Content(val location: WorkplaceLocation) : WorkplaceLocationState
}
