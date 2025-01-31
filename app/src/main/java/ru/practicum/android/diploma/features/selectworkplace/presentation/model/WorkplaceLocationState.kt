package ru.practicum.android.diploma.features.selectworkplace.presentation.model

sealed interface WorkplaceLocationState {
    object Loading : WorkplaceLocationState
    object Error : WorkplaceLocationState
    data class Success(val location: WorkplaceLocation) : WorkplaceLocationState
}
