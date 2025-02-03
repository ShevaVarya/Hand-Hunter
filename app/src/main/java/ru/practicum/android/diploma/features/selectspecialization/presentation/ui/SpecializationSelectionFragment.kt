package ru.practicum.android.diploma.features.selectspecialization.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSpecializationSelectionBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustriesState
import ru.practicum.android.diploma.features.selectspecialization.presentation.model.IndustryUI
import ru.practicum.android.diploma.features.selectspecialization.presentation.ui.adapter.SpecializationSelectionAdapter
import ru.practicum.android.diploma.features.selectspecialization.presentation.viewmodel.SpecializationSelectionViewModel
import ru.practicum.android.diploma.utils.collectWithLifecycle
import ru.practicum.android.diploma.utils.debounce

class SpecializationSelectionFragment : BaseFragment<FragmentSpecializationSelectionBinding>() {

    private var specializationAdapter: SpecializationSelectionAdapter? = null
    private var onSearchDebounce: ((String) -> Unit)? = null
    private val viewModel by viewModel<SpecializationSelectionViewModel>()

    private var selectedIndustry: IndustryUI? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSpecializationSelectionBinding {
        return FragmentSpecializationSelectionBinding.inflate(inflater, container, false)
    }

    override fun initUi() {
        initAdapter()
        initSearchDebounce()
        initListeners()
        initChooseButtonListener()
    }

    override fun observeData() {
        viewModel.getIndustriesState().collectWithLifecycle(this) { state ->
            renderState(state)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        specializationAdapter = null
    }

    private fun initAdapter() {
        specializationAdapter = SpecializationSelectionAdapter(
            onItemClick = { industryUI, position ->
                selectedIndustry = industryUI
                specializationAdapter?.updateSelectedItemPosition(position)
            },
            onSelectionChanged = { isSelected ->
                updateChooseButtonVisibility(isSelected)
            }
        )
        viewBinding.specializationRecyclerView.adapter = specializationAdapter
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
        setupToolbar()
        onTextChanged()
    }

    private fun initChooseButtonListener() {
        viewBinding.chooseButton.setOnClickListener {
            selectedIndustry?.let { industry ->
                viewModel.selectAndSaveIndustry(industry)
                goBack()
            }
        }
    }

    private fun setupToolbar() {
        viewBinding.toolbar.setOnClickListener {
            goBack()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTextChanged() {
        with(viewBinding) {
            specializationEditText.doOnTextChanged { text, _, _, _ ->
                val querySearch = text.toString().trim()
                val isEditTextNotEmpty = querySearch.isNotBlank()
                switchSearchIcon(isEditTextNotEmpty)
                onSearchDebounce?.invoke(querySearch)
            }

            specializationEditText.setOnTouchListener { _, event ->
                var handled = false

                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableEnd = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.close_24px
                    )

                    val drawableWidth = drawableEnd?.intrinsicWidth ?: 0
                    val touchAreaStart = specializationEditText.width -
                        specializationEditText.paddingEnd -
                        drawableWidth

                    if (event.rawX >= touchAreaStart) {
                        specializationEditText.text?.clear()
                        hideKeyBoard()
                        handled = true
                    }
                }
                handled
            }
        }
    }

    private fun switchSearchIcon(isEditTextNotEmpty: Boolean) {
        with(viewBinding) {
            val editTextIcon = if (isEditTextNotEmpty) {
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
            specializationEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, editTextIcon, null)
        }
    }

    private fun updateChooseButtonVisibility(isVisible: Boolean) {
        viewBinding.chooseButton.isVisible = isVisible
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        val view = activity?.currentFocus ?: view
        view?.let {
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    private fun renderState(state: IndustriesState) {
        with(viewBinding) {
            specializationRecyclerView.isVisible = false
            progressBar.isVisible = false
            errorsTextView.isVisible = false
            errorsImageView.isVisible = false
            chooseButton.isVisible = false

            when (state) {
                is IndustriesState.Content -> {
                    specializationRecyclerView.isVisible = true
                    progressBar.isVisible = false
                    specializationAdapter?.updateItems(state.industries)
                }
                is IndustriesState.Loading -> {
                    progressBar.isVisible = true
                }
                is IndustriesState.Error -> {
                    errorsTextView.isVisible = true
                    errorsImageView.isVisible = true
                }
            }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}
