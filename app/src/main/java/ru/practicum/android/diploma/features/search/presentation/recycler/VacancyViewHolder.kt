package ru.practicum.android.diploma.features.search.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import java.util.Locale

class VacancyViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vacancy: Vacancy) {
        val cornerRadius = binding.root.context.resources.getDimensionPixelSize(R.dimen.radius_1x)
        with(binding) {
            professionTextView.text = getProfession(vacancy.name, vacancy.city)
            fieldTextView.text = vacancy.employerName
            wagesTextView.text = vacancy.salaryFrom.toString()

            Glide.with(logoImageView.context)
                .load(vacancy.employerLogoUrl)
                .placeholder(R.drawable.placeholder_32px)
                .fitCenter()
                .transform(RoundedCorners(cornerRadius))
                .into(logoImageView)
        }
    }

    private fun getProfession(vacancyName: String, city: String): String {
        return String.format(Locale.getDefault(), "%s, %s", vacancyName, city)
    }

}
