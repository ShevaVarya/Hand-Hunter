package ru.practicum.android.diploma.features.team.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R

class MemberAdapter(private val members: List<String>) : RecyclerView.Adapter<MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_team_item, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val memberName = members[position]
        holder.memberNameTextView.text = memberName
    }

    override fun getItemCount(): Int {
        return members.size
    }
}
