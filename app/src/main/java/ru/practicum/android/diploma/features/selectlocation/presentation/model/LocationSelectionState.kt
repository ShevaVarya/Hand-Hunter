package ru.practicum.android.diploma.features.selectlocation.presentation.model

sealed interface LocationSelectionState {

    data class ContentCountry(
        val countries: List<CountryUI>
    ) : LocationSelectionState

    data class ContentRegion(
        val regions: List<RegionUI>
    ) : LocationSelectionState

    data object NetworkError : LocationSelectionState

    data object NoRegionError : LocationSelectionState

    data object Loading : LocationSelectionState

}
