package ru.practicum.android.diploma.features.favourite.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentFavouriteVacanciesBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.favourite.presentation.viewmodel.FavouriteVacanciesViewModel

class FavouriteVacanciesFragment : BaseFragment<FragmentFavouriteVacanciesBinding>() {

    private val viewModel: FavouriteVacanciesViewModel by viewModel<FavouriteVacanciesViewModel>()

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavouriteVacanciesBinding {
        viewBinding = FragmentFavouriteVacanciesBinding.inflate(inflater, container, false)
        return viewBinding
    }

    override fun initUi() {
        TODO()
    }

    override fun observeData() {
        TODO()
    }

}
