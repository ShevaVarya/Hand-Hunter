package ru.practicum.android.diploma.features.favourite.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentFavouriteVacanciesBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.favourite.presentation.model.FavouriteVacanciesState
import ru.practicum.android.diploma.features.favourite.presentation.viewmodel.FavouriteVacanciesViewModel
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.features.search.presentation.recycler.VacancyAdapter
import ru.practicum.android.diploma.utils.collectWithLifecycle

class FavouriteVacanciesFragment : BaseFragment<FragmentFavouriteVacanciesBinding>() {

    private val viewModel: FavouriteVacanciesViewModel by viewModel<FavouriteVacanciesViewModel>()

    private var vacancyAdapter: VacancyAdapter? = null

    private var onVacancyClickDebounce: ((Vacancy) -> Unit?)? = null

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavouriteVacanciesBinding {
        viewBinding = FragmentFavouriteVacanciesBinding.inflate(inflater, container, false)
        return viewBinding
    }

    override fun initUi() {
        initAdapters()
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) {
            applyState(it)
        }
    }

    private fun initAdapters() {
        vacancyAdapter = VacancyAdapter(
            onClick = { vacancy ->
                onVacancyClickDebounce?.let { it(vacancy) }
            }
        )

        with(viewBinding) {
            favouriteRecyclerView.adapter = vacancyAdapter
            favouriteRecyclerView.itemAnimator = null
        }
    }

    private fun applyState(it: FavouriteVacanciesState) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        vacancyAdapter = null
    }
}
