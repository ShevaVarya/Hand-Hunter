package ru.practicum.android.diploma.features.selectspecialization.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI

class IndustryDiffCallback(
    private val oldList: List<IndustryUI>,
    private val newList: List<IndustryUI>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
