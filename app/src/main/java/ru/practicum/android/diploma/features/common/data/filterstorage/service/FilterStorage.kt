package ru.practicum.android.diploma.features.common.data.filterstorage.service

import android.content.SharedPreferences
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterCountryEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterIndustryEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterMainDataEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FilterRegionEntity
import ru.practicum.android.diploma.features.common.data.filterstorage.dto.FullLocationDataEntity

interface FilterStorage {
    fun setCountry(value: FilterCountryEntity)
    fun setRegion(value: FilterRegionEntity)
    fun setIndustry(value: FilterIndustryEntity)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)

    fun getFilterMainData(): FilterMainDataEntity
    fun getFullLocationData(): FullLocationDataEntity
    fun getCountryId(): String

    fun deleteCountryData()
    fun deleteRegionData()
}

class FilterStorageImpl(
    private val sharedPrefs: SharedPreferences
) : FilterStorage {

    override fun setCountry(value: FilterCountryEntity) {
        sharedPrefs.edit()
            .putString(COUNTRY_NAME, value.name)
            .putString(COUNTRY_ID, value.id)
            .apply()
    }

    override fun setRegion(value: FilterRegionEntity) {
        sharedPrefs.edit()
            .putString(REGION_NAME, value.name)
            .putString(REGION_ID, value.id)
            .putString(REGION_PARENT_ID, value.parentId)
            .apply()
    }

    override fun setIndustry(value: FilterIndustryEntity) {
        sharedPrefs.edit()
            .putString(INDUSTRY_NAME, value.name)
            .putString(INDUSTRY_ID, value.id)
            .apply()
    }

    override fun setSalary(value: String) {
        sharedPrefs.edit()
            .putString(SALARY, value)
            .apply()
    }

    override fun setIsNeedToHideVacancyWithoutSalary(value: Boolean) {
        sharedPrefs.edit()
            .putBoolean(SHOW_WITHOUT_SALARY_FLAG, value)
            .apply()
    }

    override fun getFilterMainData(): FilterMainDataEntity {
        return FilterMainDataEntity(
            country = getCountryFromPrefs(),
            region = getRegionFromPrefs(),
            industry = getIndustryFromPrefs(),
            salary = sharedPrefs.getString(SALARY, "") ?: "",
            isNeedToHideVacancyWithoutSalary = sharedPrefs.getBoolean(SHOW_WITHOUT_SALARY_FLAG, false)
        )
    }

    override fun getFullLocationData(): FullLocationDataEntity {
        return FullLocationDataEntity(
            country = getCountryFromPrefs(),
            region = getRegionFromPrefs(),
        )
    }

    override fun getCountryId(): String {
        return getCountryFromPrefs().id
    }

    override fun deleteCountryData() {
        sharedPrefs.edit()
            .remove(COUNTRY_NAME)
            .remove(COUNTRY_ID)
            .apply()
    }

    override fun deleteRegionData() {
        sharedPrefs.edit()
            .remove(REGION_NAME)
            .remove(REGION_ID)
            .remove(REGION_PARENT_ID)
            .apply()
    }

    private fun getCountryFromPrefs() = FilterCountryEntity(
        id = sharedPrefs.getString(COUNTRY_ID, "") ?: "",
        name = sharedPrefs.getString(COUNTRY_NAME, "") ?: ""
    )

    private fun getRegionFromPrefs() = FilterRegionEntity(
        id = sharedPrefs.getString(REGION_ID, "") ?: "",
        name = sharedPrefs.getString(REGION_NAME, "") ?: "",
        parentId = sharedPrefs.getString(REGION_PARENT_ID, "") ?: ""
    )

    private fun getIndustryFromPrefs() = FilterIndustryEntity(
        id = sharedPrefs.getString(INDUSTRY_ID, "") ?: "",
        name = sharedPrefs.getString(INDUSTRY_NAME, "") ?: ""
    )

    companion object {
        private const val COUNTRY_NAME = "country_name"
        private const val COUNTRY_ID = "country_id"
        private const val REGION_NAME = "region_name"
        private const val REGION_ID = "region_id"
        private const val REGION_PARENT_ID = "region_parent_id"
        private const val INDUSTRY_NAME = "industry_name"
        private const val INDUSTRY_ID = "industry_id "
        private const val SALARY = "salary"
        private const val SHOW_WITHOUT_SALARY_FLAG = "showWithoutSalary"
    }
}
