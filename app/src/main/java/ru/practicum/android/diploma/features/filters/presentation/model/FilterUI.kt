package ru.practicum.android.diploma.features.filters.presentation.model

import ru.practicum.android.diploma.features.selectlocation.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.RegionUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

/**
 * @param country `Nullable` Хранит экземпляр класса `CountryUI` с информацией о стране
 * @param region `Nullable` Хранит экземпляр класса `RegionUI` с информацией о регионе
 * @param industry `Nullable` Хранит экземпляр класса `IndustryUI` с информацией о регионе
 * @param salary `Nullable` Хранит информацию об уровне зарплаты
 * @param onlyWithSalary  Хранит флаг, указывающий на необходимость игнорирования вакансий без указания зарплаты
 * @property isDefault  Хранит флаг, указывающий на то, являются ли значения фильтра значениями по умолчанию
 */

data class FilterUI(
    var country: CountryUI? = null,
    var region: RegionUI? = null,
    var industry: IndustryUI? = null,
    var salary: Int? = null,
    var onlyWithSalary: Boolean = false
) {
    val isDefault: Boolean
        get() = country == null && region == null && industry == null && onlyWithSalary == false && salary == null
}
