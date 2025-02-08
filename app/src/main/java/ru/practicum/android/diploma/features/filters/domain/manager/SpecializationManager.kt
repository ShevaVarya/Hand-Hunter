package ru.practicum.android.diploma.features.filters.domain.manager

import ru.practicum.android.diploma.features.filters.domain.api.filter.FilterRepository
import ru.practicum.android.diploma.features.filters.domain.model.Industry

interface SpecializationManager {
    fun getIndustry(): Industry
    fun keepIndustry(industry: Industry)
    fun acceptData()
}

class SpecializationManagerImpl(
    private val filterRepository: FilterRepository,
    private val filterManager: FilterManager
): SpecializationManager {
    private var industry: Industry = getIndustryFromPrefs()  ?: Industry (
        id = null,
        name = null
    )

    override fun getIndustry(): Industry {
        return industry
    }

    override fun keepIndustry(industry: Industry) {
        this.industry = industry
    }

    override fun acceptData() {
        filterManager.keepIndustry(industry)
    }

    private fun getIndustryFromPrefs(): Industry? {
        return filterRepository.getSavedIndustry()
    }
}
