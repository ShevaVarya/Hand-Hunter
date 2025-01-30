package ru.practicum.android.diploma.features.selectspecialization.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemSpecializationBinding
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.ui.SpecializationSelectionViewHolder

class SpecializationSelectionAdapter(
    private val onItemClick: (IndustryUI) -> Unit
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
        val item = items[holder.getAdapterPosition()]
        holder.bind(item, holder.getAdapterPosition(), selectedItemPosition)
        holder.itemView.setOnClickListener {
            onItemClick(item)
            val previousSelectedItemPosition = selectedItemPosition
            selectedItemPosition = holder.getAdapterPosition()
            notifyItemChanged(previousSelectedItemPosition)
            notifyItemChanged(selectedItemPosition)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<IndustryUI>) {
        val diffCallback = IndustryDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}
