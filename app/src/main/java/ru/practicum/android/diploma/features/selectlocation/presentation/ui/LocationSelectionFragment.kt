package ru.practicum.android.diploma.features.selectlocation.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocationSelectionBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.selectlocation.presentation.model.CountryUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.LocationSelectionState
import ru.practicum.android.diploma.features.selectlocation.presentation.model.RegionUI
import ru.practicum.android.diploma.features.selectlocation.presentation.model.Regionable
import ru.practicum.android.diploma.features.selectlocation.presentation.recycler.LocationAdapter
import ru.practicum.android.diploma.features.selectlocation.presentation.viewmodel.LocationSelectionViewModel
import ru.practicum.android.diploma.utils.collectWithLifecycle
import ru.practicum.android.diploma.utils.debounce

class LocationSelectionFragment : BaseFragment<FragmentLocationSelectionBinding>() {

    private val isCountry by lazy {
        arguments?.getBoolean(IS_COUNTRY)
    }

    private val countryId by lazy {
        arguments?.getString(COUNTRY_ID)
    }

    private val viewModel: LocationSelectionViewModel by viewModel<LocationSelectionViewModel> {
        parametersOf(isCountry, countryId)
    }

    private var locationAdapter: LocationAdapter? = null

    private var onLocationClickDebounce: ((Regionable) -> Unit?)? = null
    private var onSearchDebounce: ((String) -> Unit)? = null

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocationSelectionBinding {
        return FragmentLocationSelectionBinding.inflate(inflater, container, false)
    }

    override fun initUi() {
        initAdapter()
        initClickDebounce()
        initSearchDebounce()
        initListeners()
        viewModel.getData()
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) {
            applyState(it)
        }
    }

    private fun applyState(state: LocationSelectionState) {
        with(viewBinding) {
            progressBar.isGone = true
            contentRecyclerView.isGone = true
            errorContainer.isGone = true
            searchEditText.isGone = true
        }

        when (state) {
            is LocationSelectionState.ContentCountry -> showContentCountry(state.countries)
            is LocationSelectionState.ContentRegion -> showContentRegion(state.regions)
            is LocationSelectionState.Loading -> showLoading()
            is LocationSelectionState.NetworkError -> showNetworkError()
            is LocationSelectionState.NoRegionError -> showNoRegionError()
        }
    }

    private fun showContentCountry(countries: List<CountryUI>) {
        locationAdapter?.submitList(countries)
        viewBinding.contentRecyclerView.isVisible = true
        viewBinding.toolbar.title = getString(R.string.location_country_toolbar_title)
    }

    private fun showContentRegion(regions: List<RegionUI>) {
        locationAdapter?.submitList(regions)
        viewBinding.contentRecyclerView.isVisible = true
        viewBinding.searchEditText.isVisible = true
        viewBinding.toolbar.title = getString(R.string.location_region_toolbar_title)
    }

    private fun showLoading() {
        viewBinding.progressBar.isVisible = true
    }

    private fun showNetworkError() {
        with(viewBinding) {
            errorContainer.isVisible = true
            messageErrorTextView.setText(R.string.location_server_error)
            errorImageView.setImageResource(R.drawable.something_went_wrong)
        }
    }

    private fun showNoRegionError() {
        with(viewBinding) {
            errorContainer.isVisible = true
            messageErrorTextView.setText(R.string.location_no_region_error)
            errorImageView.setImageResource(R.drawable.bad_search)
        }
    }

    private fun initAdapter() {
        locationAdapter = LocationAdapter(
            onClick = { region ->
                onLocationClickDebounce?.let { it(region) }
            }
        )

        with(viewBinding) {
            contentRecyclerView.adapter = locationAdapter
            contentRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            contentRecyclerView.itemAnimator = null
        }

        locationAdapter?.submitList(emptyList())
    }

    private fun initClickDebounce() {
        onLocationClickDebounce = debounce(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { region ->
            goBack(region)
        }
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

    private fun initListeners() {
        viewBinding.toolbar.setOnClickListener {
            goBack(null)
        }

        onTextChanged()
        clearSearchString()
    }

    private fun goBack(region: Regionable?) {
        region?.let {
            viewModel.saveRegion(region)
        }
        parentFragmentManager.popBackStack()
    }

    private fun onTextChanged() {
        with(viewBinding) {
            searchEditText.doOnTextChanged { text, _, _, _ ->
                val querySearch = text.toString()
                val isEditTextNotEmpty = text.isNullOrEmpty().not()
                switchSearchClearIcon(isEditTextNotEmpty)
                onSearchDebounce?.invoke(querySearch)
            }
        }
    }

    private fun switchSearchClearIcon(isEditTextNotEmpty: Boolean) {
        with(viewBinding) {
            val icon = if (isEditTextNotEmpty) {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.close_24px
                )
            } else {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.search_24px
                )
            }
            searchEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
        }
    }

    @Suppress("LabeledExpression")
    @SuppressLint("ClickableViewAccessibility")
    private fun clearSearchString() {
        viewBinding.searchEditText.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = viewBinding.searchEditText.compoundDrawables[2] // DrawableEnd (Right)
                if (drawableEnd != null) {
                    val drawableStartX =
                        viewBinding.searchEditText.width -
                            viewBinding.searchEditText.paddingEnd -
                            drawableEnd.intrinsicWidth
                    if (event.rawX >= drawableStartX) {
                        viewBinding.searchEditText.text.clear()
                        hideKeyBoard()
                        view.performClick()
                        return@setOnTouchListener true
                    }
                }
            }
            return@setOnTouchListener false
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

    override fun onDestroyView() {
        super.onDestroyView()
        locationAdapter = null
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 100L
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val IS_COUNTRY = "is_country"
        private const val COUNTRY_ID = "country_id"
        const val REQUEST_KEY = "request_key"
        const val RESULT_KEY = "region_id"

        fun createArgs(isCountry: Boolean, countryId: String?): Bundle {
            return androidx.core.os.bundleOf(
                IS_COUNTRY to isCountry,
                COUNTRY_ID to countryId
            )
        }
    }
}
