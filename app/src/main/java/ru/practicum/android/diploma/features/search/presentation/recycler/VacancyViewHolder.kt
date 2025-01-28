package ru.practicum.android.diploma.features.search.presentation.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.features.search.presentation.model.VacancySearchUI

class VacancyViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vacancy: VacancySearchUI) {
        Log.d("MyLog", "bind: $vacancy")
        val cornerRadius = binding.root.context.resources.getDimensionPixelSize(R.dimen.radius_1x)
        with(binding) {
            professionTextView.text = vacancy.formatedProfession
            fieldTextView.text = vacancy.employerName
            wagesTextView.text = vacancy.formatedSalary

            Glide.with(logoImageView.context)
                .load(vacancy.employerLogoUrl)
                .placeholder(R.drawable.placeholder_32px)
                .fitCenter()
                .transform(RoundedCorners(cornerRadius))
                .into(logoImageView)
        }
    }

}
