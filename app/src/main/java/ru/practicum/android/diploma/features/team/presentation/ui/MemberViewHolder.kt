package ru.practicum.android.diploma.features.team.presentation.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val memberNameTextView: TextView = itemView.findViewById(R.id.memberNameTextView)
}
