package ru.practicum.android.diploma.features.favourite.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavouriteVacanciesBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.favourite.presentation.model.FavouriteVacanciesState
import ru.practicum.android.diploma.features.favourite.presentation.model.FavouriteVacancyUI
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

    private fun applyState(state: FavouriteVacanciesState) {
        with(viewBinding) {
            progressBar.isGone = true
            groupOfErrorContainer.isGone = true
            favouriteRecyclerView.isGone = true
        }

        when (state) {
            is FavouriteVacanciesState.Content -> showContent(state.vacancies)
            is FavouriteVacanciesState.Empty -> showEmpty()
            is FavouriteVacanciesState.Loading -> showLoading()
        }
    }

    private fun showContent(vacancies: List<FavouriteVacancyUI>) {
        viewBinding.favouriteRecyclerView.isVisible = true
        //vacancyAdapter?.submitList(vacancies)
    }

    private fun showLoading() {
        viewBinding.progressBar.isVisible = true
    }

    private fun showEmpty() {
        with(viewBinding) {
            groupOfErrorContainer.isVisible = true
            messageErrorTextView.setText(R.string.message_error_empty_data)
            errorImageView.setImageResource(R.drawable.empty_list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vacancyAdapter = null
    }
}
