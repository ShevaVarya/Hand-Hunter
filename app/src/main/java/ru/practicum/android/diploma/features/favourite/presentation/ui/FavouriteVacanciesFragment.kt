package ru.practicum.android.diploma.features.favourite.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.databinding.FragmentFavouriteVacanciesBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment

class FavouriteVacanciesFragment : BaseFragment<FragmentFavouriteVacanciesBinding>() {

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavouriteVacanciesBinding {
        viewBinding = FragmentFavouriteVacanciesBinding.inflate(inflater, container, false)
        return viewBinding
    }

    override fun initUi() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }

}
