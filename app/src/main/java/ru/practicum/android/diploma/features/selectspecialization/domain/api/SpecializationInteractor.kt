package ru.practicum.android.diploma.features.selectspecialization.domain.api

import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

interface SpecializationInteractor {
    fun setIndustry(industry: Industry)
}
