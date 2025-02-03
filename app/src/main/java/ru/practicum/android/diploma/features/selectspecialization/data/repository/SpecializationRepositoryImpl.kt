package ru.practicum.android.diploma.features.selectspecialization.data.repository

import ru.practicum.android.diploma.features.common.data.filterstorage.service.FilterStorage
import ru.practicum.android.diploma.features.selectspecialization.data.dto.toFilterEntity
import ru.practicum.android.diploma.features.selectspecialization.domain.api.SpecializationRepository
import ru.practicum.android.diploma.features.selectspecialization.domain.model.Industry

class SpecializationRepositoryImpl(
    private val filterStorage: FilterStorage
) : SpecializationRepository {

    override fun setIndustry(industry: Industry) {
        filterStorage.setIndustry(industry.toFilterEntity())
    }
}
