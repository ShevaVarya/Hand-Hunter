package ru.practicum.android.diploma.features.filters.data.service

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.practicum.android.diploma.features.filters.data.dto.FilterCountryEntity
import ru.practicum.android.diploma.features.filters.data.dto.FilterIndustryEntity
import ru.practicum.android.diploma.features.filters.data.dto.FilterMainDataEntity
import ru.practicum.android.diploma.features.filters.data.dto.FilterRegionEntity
import ru.practicum.android.diploma.features.filters.data.dto.FullLocationDataEntity

@Suppress("TooManyFunctions")
interface FilterStorage {
    fun setCountry(value: FilterCountryEntity)
    fun setRegion(value: FilterRegionEntity)
    fun setIndustry(value: FilterIndustryEntity)
    fun setSalary(value: String)
    fun setIsNeedToHideVacancyWithoutSalary(value: Boolean)

    fun deleteFilterMainData()
    fun deleteIndustry()
    fun deleteSalary()
    fun deleteShowWithoutSalaryFlag()
    fun getFilterMainData(): FilterMainDataEntity
    fun getFullLocationData(): FullLocationDataEntity?
    fun getCountryId(): String?

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

    @Suppress("ComplexCondition")
    override fun getFilterMainData(): FilterMainDataEntity {
        val country = getCountryFromPrefs()
        val region = getRegionFromPrefs()
        val industry = getIndustryFromPrefs()
        val salary = sharedPrefs.getString(SALARY, null)
        val isNeedToHideVacancyWithoutSalary = sharedPrefs.getBoolean(SHOW_WITHOUT_SALARY_FLAG, false)
        return FilterMainDataEntity(country, region, industry, salary, isNeedToHideVacancyWithoutSalary)
    }

    override fun getFullLocationData(): FullLocationDataEntity? {
        val country = getCountryFromPrefs()
        val region = getRegionFromPrefs()
        return if (isCountryAndRegionNull(country, region)) {
            null
        } else {
            FullLocationDataEntity(country, region)
        }
    }

    override fun getCountryId(): String? {
        return getCountryFromPrefs()?.id
    }

    override fun deleteFilterMainData() {
        sharedPrefs.edit {
            remove(COUNTRY_ID)
            remove(COUNTRY_NAME)
            remove(REGION_ID)
            remove(REGION_NAME)
            remove(REGION_PARENT_ID)
            remove(INDUSTRY_NAME)
            remove(INDUSTRY_ID)
            remove(SALARY)
            remove(SHOW_WITHOUT_SALARY_FLAG)
        }
    }

    override fun deleteIndustry() {
        sharedPrefs.edit {
            remove(INDUSTRY_ID)
            remove(INDUSTRY_NAME)
        }
    }

    override fun deleteSalary() {
        sharedPrefs.edit {
            remove(SALARY)
        }
    }

    override fun deleteShowWithoutSalaryFlag() {
        sharedPrefs.edit {
            remove(SHOW_WITHOUT_SALARY_FLAG)
        }
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

    private fun getCountryFromPrefs(): FilterCountryEntity? {
        val id = sharedPrefs.getString(COUNTRY_ID, null)
        val name = sharedPrefs.getString(COUNTRY_NAME, null)
        return if (id.isNullOrEmpty() && name.isNullOrEmpty()) {
            null
        } else {
            FilterCountryEntity(id, name)
        }
    }

    private fun getRegionFromPrefs(): FilterRegionEntity? {
        val id = sharedPrefs.getString(REGION_ID, null)
        val name = sharedPrefs.getString(REGION_NAME, null)
        val parentId = sharedPrefs.getString(REGION_PARENT_ID, null)

        return if (id.isNullOrEmpty() && name.isNullOrEmpty() && parentId.isNullOrEmpty()) {
            null
        } else {
            FilterRegionEntity(id, name, parentId)
        }
    }

    private fun getIndustryFromPrefs(): FilterIndustryEntity? {
        val id = sharedPrefs.getString(INDUSTRY_ID, null)
        val name = sharedPrefs.getString(INDUSTRY_NAME, null)
        return if (id.isNullOrEmpty() && name.isNullOrEmpty()) {
            null
        } else {
            FilterIndustryEntity(id, name)
        }
    }

    private fun isCountryAndRegionNull(country: FilterCountryEntity?, region: FilterRegionEntity?): Boolean {
        return country == null && region == null
    }

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
