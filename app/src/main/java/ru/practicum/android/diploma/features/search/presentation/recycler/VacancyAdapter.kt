package ru.practicum.android.diploma.features.search.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.features.search.presentation.model.VacancySearchUI

class VacancyAdapter(
    private val onClick: (VacancySearchUI) -> Unit = {},
) : ListAdapter<VacancySearchUI, VacancyViewHolder>(VacancyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.bind(vacancy)
        holder.itemView.setOnClickListener { onClick(vacancy) }
    }

}
