package ru.practicum.android.diploma.features.common.data.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.features.common.data.network.dto.area.AreaEntity
import ru.practicum.android.diploma.features.common.data.network.dto.area.CountryEntity
import ru.practicum.android.diploma.features.common.data.network.dto.industry.IndustryEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.VacanciesEntity
import ru.practicum.android.diploma.features.common.data.network.dto.vacancy.details.DetailsVacancyEntity

interface HHApi {
    /**
     * Запрос на получение списка вакансий как с учетом фильтров, так и без.
     *
     * Диапазон [page] и [perPage] рассчитывается из ограничения: глубина возвращаемых результатов не может быть больше 2000. Например, возможен запрос per_page=10&page=199 (выдача с 1991 по 2000 вакансию), но запрос с per_page=10&page=200 вернёт ошибку (выдача с 2001 по 2010 вакансию).
     *
     * Список возможных передаваемых параметров:
     *
     * **text**: String - Текст запроса из поля поиска;
     *
     * **area**: String - Регион. Необходимо передавать id из справочника [смотреть здесь](https://api.hh.ru/openapi/redoc#tag/Obshie-spravochniki/operation/get-areas). Можно указать несколько значений;
     *
     * **industry**: String - Отрасли компании, разместившей вакансию. Необходимо передавать id из справочника [смотреть здесь](https://api.hh.ru/openapi/redoc#tag/Obshie-spravochniki/operation/get-industries). Можно указать несколько значений;
     *
     * **salary**: Int - Размер заработной платы. Если указано это поле, но не указано **currency**, то для currency используется значение RUR. При указании значения будут найдены вакансии, в которых вилка зарплаты близка к указанной в запросе;
     *
     * **only_with_salary**: Boolean - Показывать вакансии только с указанием зарплаты;
     *
     * **locate**: String - Язык, на котором будут приходить вакансии, по умолчанию RU;
     *
     * **host**: String - Сайт, с которого берутся вакансии, по умолчанию hh.ru;
     *
     * @param page - страница, которую требуется получить, принимает число в диапазоне о 0 до 99 включительно.
     * @param perPage - сколько результатов размещено на одной странице, фиксированное значение равное 20.
     * @param params - список Query-параметров в виде Map<Key, Value>.
     */
    @GET("/vacancies")
    suspend fun getVacancies(
        @Query("text") text: String,
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 20,
        @QueryMap params: Map<String, String>
    ): VacanciesEntity

    /**
     * Запрос, возвращающий детализированный вариант конкретной вакансии.
     *
     * Список возможных передаваемых параметров:
     *
     * **locate**: String - Язык, на котором будут приходить вакансии, по умолчанию RU;
     *
     * **host**: String - Сайт, с которого берутся вакансии, по умолчанию hh.ru;
     *
     * @param vacancyId - идентификатор вакансии (обязательный параметр).
     * @param params - список Query-параметров в виде Map<Key, Value>.
     */
    @GET("vacancies/{vacancyId}")
    suspend fun getVacancyDetailsById(
        @Path("vacancyId") vacancyId: String,
        @QueryMap params: Map<String, String>
    ): Result<DetailsVacancyEntity>

    /**
     * Запрос, возвращающий список стран.
     *
     * Список возможных передаваемых параметров:
     *
     * **locate**: String - Язык, на котором будут приходить вакансии, по умолчанию RU;
     *
     * **host**: String - Сайт, с которого берутся вакансии, по умолчанию hh.ru;
     *
     * @param params - список Query-параметров в виде Map<Key, Value>.
     */
    @GET("/areas/countries")
    fun getCountriesList(
        @QueryMap params: Map<String, Any>
    ): Call<List<CountryEntity>>

    /**
     * Запрос, возвращающий древовидный список всех регионов.
     *
     * Список возможных передаваемых параметров:
     *
     * **locate**: String - Язык, на котором будут приходить вакансии, по умолчанию RU;
     *
     * **host**: String - Сайт, с которого берутся вакансии, по умолчанию hh.ru;
     *
     * @param params - список Query-параметров в виде Map<Key, Value>.
     */
    @GET("/areas")
    fun getAllAreasList(
        @QueryMap params: Map<String, Any>
    ): Call<List<AreaEntity>>

    /**
     * Запрос, возвращающий двухуровневый справочник всех отраслей(список отраслей, содержащих в себе список подотраслей).
     *
     * Список возможных передаваемых параметров:
     *
     * **locate**: String - Язык, на котором будут приходить вакансии, по умолчанию RU;
     *
     * **host**: String - Сайт, с которого берутся вакансии, по умолчанию hh.ru;
     *
     * @param params - список Query-параметров в виде Map<Key, Value>.
     */
    @GET("/industries")
    fun getAllIndustriesList(
        @QueryMap params: Map<String, Any>
    ): Call<List<IndustryEntity>>
}
