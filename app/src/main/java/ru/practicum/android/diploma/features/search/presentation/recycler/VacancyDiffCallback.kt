package ru.practicum.android.diploma.features.search.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.features.search.domain.model.Vacancy

class VacancyDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}
