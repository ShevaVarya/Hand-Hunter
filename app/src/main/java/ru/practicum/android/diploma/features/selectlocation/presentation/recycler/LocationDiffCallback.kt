package ru.practicum.android.diploma.features.selectlocation.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.features.selectlocation.presentation.model.Regionable

class LocationDiffCallback : DiffUtil.ItemCallback<Regionable>() {

    override fun areItemsTheSame(oldItem: Regionable, newItem: Regionable): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Regionable, newItem: Regionable): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}
