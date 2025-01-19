package ru.practicum.android.diploma.features.common.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeySkillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    @ColumnInfo(name = "key_skill")
    val keySkill: String
)
