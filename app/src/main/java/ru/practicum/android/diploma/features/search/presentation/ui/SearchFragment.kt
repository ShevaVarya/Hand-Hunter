package ru.practicum.android.diploma.features.search.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.search.domain.model.QuerySearch
import ru.practicum.android.diploma.features.search.domain.model.Vacancies
import ru.practicum.android.diploma.features.search.domain.model.Vacancy
import ru.practicum.android.diploma.features.search.presentation.model.SearchState
import ru.practicum.android.diploma.features.search.presentation.recycler.VacancyAdapter
import ru.practicum.android.diploma.features.search.presentation.viewmodel.SearchViewModel
import ru.practicum.android.diploma.features.vacancy.presentation.ui.VacancyInfoFragment
import ru.practicum.android.diploma.utils.debounce


class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private var vacancyAdapter: VacancyAdapter? = null

    private var onVacancyClickDebounce: ((Vacancy) -> Unit?)? = null
    private var onSearchDebounce: ((QuerySearch) -> Unit)? = null

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<SearchViewModel>()

    override fun onDestroyView() {
        super.onDestroyView()
        vacancyAdapter = null
    }

    override fun initUi() {
        initSearchDebounce()
        initClickDebounce()
        initAdapters()
        initListeners()
    }

    override fun observeData() {
        viewModel.getSearchStateFlow()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { searchState ->
                when (searchState) {
                    SearchState.Loading -> showProgressBar()
                    is SearchState.Content -> showVacancies(searchState.vacancies)
                    SearchState.Init -> showInit()
                    SearchState.EmptyError -> showEmptyError()
                    SearchState.NetworkError -> showNetworkError()
                    SearchState.ServerError -> showServerError()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showProgressBar() {
        with(viewBinding) {
            progressBar.isVisible = true
            contentRecyclerView.isVisible = false
            messageTextView.isVisible = false
            errorsImageView.isVisible = false
            errorsTextView.isVisible = false
        }
    }

    private fun showVacancies(vacancies: Vacancies) {
        vacancyAdapter?.submitList(vacancies.items)
        with(viewBinding) {
            messageTextView.text = getTotalVacanciesText(vacancies)
            progressBar.isVisible = false
            contentRecyclerView.isVisible = true
            messageTextView.isVisible = true
            errorsImageView.isVisible = false
            errorsTextView.isVisible = false
        }
    }

    private fun showEmptyError() {
        with(viewBinding) {
            errorsImageView.setImageResource(R.drawable.bad_search)
            errorsTextView.setText(R.string.bad_request)
            messageTextView.setText(R.string.message_text)
            progressBar.isVisible = false
            contentRecyclerView.isVisible = false
            messageTextView.isVisible = true
            errorsImageView.isVisible = true
            errorsTextView.isVisible = true
        }
    }

    private fun showNetworkError() {
        with(viewBinding) {
            errorsImageView.setImageResource(R.drawable.no_internet)
            errorsTextView.setText(R.string.bad_internet)
            progressBar.isVisible = false
            contentRecyclerView.isVisible = false
            messageTextView.isVisible = false
            errorsImageView.isVisible = true
            errorsTextView.isVisible = true
        }
    }

    private fun showServerError() {
        with(viewBinding) {
            errorsImageView.setImageResource(R.drawable.server_error)
            errorsTextView.setText(R.string.server_error)
            progressBar.isVisible = false
            contentRecyclerView.isVisible = false
            messageTextView.isVisible = false
            errorsImageView.isVisible = true
            errorsTextView.isVisible = true
        }
    }

    private fun showInit() {
        vacancyAdapter?.submitList(emptyList())
        with(viewBinding) {
            messageTextView.text = EMPTY_TEXT
            errorsImageView.setImageResource(R.drawable.empty_search)
            progressBar.isVisible = false
            contentRecyclerView.isVisible = false
            messageTextView.isVisible = false
            errorsImageView.isVisible = true
            errorsTextView.isVisible = true

            searchEditText.setText(EMPTY_TEXT)
            searchEditText.clearFocus()
        }
        hideKeyBoard()
    }

    private fun getTotalVacanciesText(vacancies: Vacancies): String {
        val vacanciesCount = vacancies.found
        return resources.getQuantityString(
            R.plurals.vacancies_count,
            vacanciesCount,
            vacanciesCount
        )
    }

    private fun initSearchDebounce() {
        onSearchDebounce = debounce(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            true
        ) {
            viewModel.search(it)
        }
    }

    private fun initClickDebounce() {
        onVacancyClickDebounce = debounce(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { vacancy ->
            startVacancyInfoFragment(vacancy.id)
        }
    }

    private fun startVacancyInfoFragment(vacancyId: String) {
        findNavController()
            .navigate(
                R.id.action_searchFragment_to_vacancyInfoFragment,
                VacancyInfoFragment.createArgs(vacancyId)
            )
    }

    private fun initAdapters() {
        vacancyAdapter = VacancyAdapter(
            onClick = { vacancy ->
                onVacancyClickDebounce?.let { it(vacancy) }
            }
        )

        with(viewBinding) {
            contentRecyclerView.adapter = vacancyAdapter
            contentRecyclerView.itemAnimator = null
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        with(viewBinding) {
            searchEditText.setOnTouchListener { _, event ->
                if (event.action === MotionEvent.ACTION_UP) {
                    if (searchEditText.compoundDrawables[RIGHT_CORNER] != null) {
                        val drawableEndWidth =
                            searchEditText.compoundDrawables[RIGHT_CORNER].bounds.width()

                        if (event.x >= (searchEditText.width - searchEditText.paddingEnd - drawableEndWidth)) {
                            viewModel.onClearedSearch()
                            return@setOnTouchListener true
                        }
                    }
                }
                false
            }

            searchEditText.doOnTextChanged { text, _, _, _ ->
                val isNotEmpty = text.isNullOrEmpty().not()
                val querySearch = QuerySearch(text = text.toString())
                if (!isNotEmpty) {
                    viewModel.onClearedSearch()
                }

                val drawableEnd = if (isNotEmpty) {
                    ContextCompat.getDrawable(requireContext(), R.drawable.close_24px)
                } else {
                    ContextCompat.getDrawable(requireContext(), R.drawable.search_24px)
                }
                searchEditText.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    drawableEnd,
                    null
                )
                onSearchDebounce?.invoke(querySearch)
            }
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        val view = activity?.currentFocus ?: view
        view?.let {
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private companion object {
        private const val EMPTY_TEXT = ""
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val CLICK_DEBOUNCE_DELAY = 100L
        private const val RIGHT_CORNER = 2
    }

}
