package ru.practicum.android.diploma.features.selectspecialization.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemSpecializationBinding
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

class SpecializationSelectionAdapter(
    private val onItemClick: (IndustryUI, Int) -> Unit,
    private val onSelectionChanged: (Boolean) -> Unit
) : RecyclerView.Adapter<SpecializationSelectionViewHolder>() {

    private val items: MutableList<IndustryUI> = mutableListOf()
    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecializationSelectionViewHolder {
        val binding = ItemSpecializationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpecializationSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecializationSelectionViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position == selectedItemPosition)
        holder.itemView.setOnClickListener {
            onItemClick(item, position)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<IndustryUI>) {
        items.clear()
        items.addAll(newItems)
        selectedItemPosition = -1
        notifyDataSetChanged()
    }

    fun updateSelectedItemPosition(position: Int) {
        val previousSelectedItemPosition = selectedItemPosition
        selectedItemPosition = if (position == selectedItemPosition) {
            -1
        } else {
            position
        }
        notifyItemChanged(previousSelectedItemPosition)
        notifyItemChanged(selectedItemPosition)

        onSelectionChanged(selectedItemPosition != -1)
    }
}

