package ru.practicum.android.diploma.features.selectspecialization.presentation.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemSpecializationBinding
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

class SpecializationSelectionViewHolder(
    private val binding: ItemSpecializationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: IndustryUI, adapterPosition: Int, selectedItemPosition: Int) {
        with(binding) {
            specializationItem.text = item.name
            val drawableEnd = if (adapterPosition == selectedItemPosition) {
                ContextCompat.getDrawable(binding.root.context, R.drawable.radio_button_on_24px)
            } else {
                ContextCompat.getDrawable(binding.root.context, R.drawable.radio_button_off_24px)
            }
            binding.specializationItem.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableEnd, null)
        }
    }
}
