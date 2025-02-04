package ru.practicum.android.diploma.features.selectspecialization.domain.api

import ru.practicum.android.diploma.features.common.domain.filter.model.FilterIndustry
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

fun Industry.toFilterIndustry(): FilterIndustry {
    return FilterIndustry(
        id = this.id,
        name = this.name
    )
}
