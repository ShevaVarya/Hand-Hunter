package ru.practicum.android.diploma.features.filters.presentation.ui.location.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.features.filters.presentation.model.api.Regionable

class LocationDiffCallback : DiffUtil.ItemCallback<Regionable>() {

    override fun areItemsTheSame(oldItem: Regionable, newItem: Regionable): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Regionable, newItem: Regionable): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}
