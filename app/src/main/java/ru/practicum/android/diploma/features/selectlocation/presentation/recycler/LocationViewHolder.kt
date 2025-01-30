package ru.practicum.android.diploma.features.selectlocation.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemLocationSelectionBinding
import ru.practicum.android.diploma.features.selectlocation.presentation.model.Regionable

class LocationViewHolder(
    private val binding: ItemLocationSelectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Regionable) {
        binding.locationTextView.text = model.name
    }

}
