package ru.practicum.android.diploma.features.selectlocation.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.ItemLocationSelectionBinding
import ru.practicum.android.diploma.features.selectlocation.presentation.model.Regionable

class LocationAdapter(
    private val onClick: (Regionable) -> Unit = {}
) : ListAdapter<Regionable, LocationViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationSelectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val region = getItem(position)
        holder.bind(region)
        holder.itemView.setOnClickListener { onClick(region) }
    }
}
