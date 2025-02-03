package ru.practicum.android.diploma.features.filters.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchFiltersBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI
import ru.practicum.android.diploma.features.filters.presentation.viewmodel.SearchFilterViewModel
import ru.practicum.android.diploma.utils.collectWithLifecycle

class SearchFiltersFragment : BaseFragment<FragmentSearchFiltersBinding>() {

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchFiltersBinding {
        return FragmentSearchFiltersBinding.inflate(inflater, container, false)
    }

    private val viewModel: SearchFilterViewModel by viewModel<SearchFilterViewModel>()

    override fun onStart() {
        super.onStart()
        viewModel.getData()
    }

    override fun initUi() {
        initializeViews()
    }

    override fun observeData() {
        viewModel.stateFilterUI.collectWithLifecycle(this) { filterUI ->
            viewModel.currentFilterUI = filterUI
            processFilterResult(filterUI)
            setupClearButton(filterUI?.country, viewBinding.placeOfWorkContainer) { viewModel.clearPlaceOfWork() }
            setupClearButton(filterUI?.industry, viewBinding.industryContainer) { viewModel.clearIndustry() }
        }
    }

    private fun initializeViews() {
        with(viewBinding) {

            placeOfWorkEditText.setOnClickListener {
                findNavController().navigate(R.id.action_searchFiltersFragment_to_workplaceSelectionFragment)
            }

            industryEditText.setOnClickListener {
                findNavController().navigate(R.id.action_searchFiltersFragment_to_specializationSelectionFragment)
            }

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            resetButton.setOnClickListener {
                viewModel.clearFilter()
            }

            acceptButton.setOnClickListener {
                viewModel.retrySearchQueryWithFilterSearch()
                findNavController().navigateUp()
            }

            salaryEditText.doOnTextChanged { s, _, _, _ ->
                setButtonVisibility(viewModel.currentFilterUI)
                viewModel.salaryEnterTextChanged(s)
                salaryEnterClearIcon(s)
            }

            withoutSalary.setOnClickListener {
                viewModel.setOnlyWithSalary(withoutSalary.isChecked)
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

    private fun salaryEnterClearIcon(text: CharSequence?) {
        with(viewBinding) {
            if (text?.isBlank() == false) {
                salaryFrameContainer.endIconMode = END_ICON_CLEAR_TEXT
                salaryFrameContainer.setEndIconDrawable(R.drawable.close_24px)
                salaryFrameContainer.setEndIconOnClickListener {
                    salaryEditText.text?.clear()
                    salaryEditText.clearFocus()
                    hideKeyBoard()
                }
            } else {
                salaryFrameContainer.endIconMode = END_ICON_NONE
                salaryFrameContainer.endIconDrawable = null
            }
        }
    }

    // Метод для отображения кнопки "очищения" у полей "Место работы" "Отрасль"
    private fun <T> setupClearButton(item: T?, til: TextInputLayout, action: () -> Unit) {
        if (item != null) {
            til.setEndIconDrawable(R.drawable.close_24px)
            til.setEndIconOnClickListener {
                action.invoke()
                til.setEndIconOnClickListener(null)
            }
        } else {
            til.setEndIconDrawable(R.drawable.arrow_forward_24px)
            til.isEndIconVisible = false
            til.isEndIconVisible = true
        }
    }

    // Метод для заполнения полей, иначе если значение по умолчанию - то поля очищаются
    private fun processFilterResult(filter: FilterUI?) {
        with(viewBinding) {
            setButtonVisibility(filter)
            if (filter?.isDefault == false) {
                processArea(filter.country, filter.region)
                industryEditText.setText(filter.industry ?: "")
                withoutSalary.isChecked = filter.onlyWithSalary
                val newSalary = filter.salary
                if (newSalary != viewModel.oldSalary) {
                    salaryEditText.setText(newSalary.toString())
                }
            } else {
                placeOfWorkEditText.text = null
                industryEditText.text = null
                withoutSalary.isChecked = false
                salaryEditText.text = null
            }
        }
        setCheckedIcon(filter?.onlyWithSalary ?: false)
    }

//    Метод для сохранения страны и региона в "Место работы"
    private fun processArea(country: String?, region: String?) {
        var result = ""
        if (country != null) result += country
        if (region != null) result += ", ${region}"
        viewBinding.placeOfWorkEditText.setText(result)
    }

    // Метод отвечающий за отображение кнопок "Применить" "Сбросить"
    private fun setButtonVisibility(filterUI: FilterUI?): Unit = with(viewBinding) {
        resetButton.isVisible = if (filterUI?.isDefault == true) false else true
        acceptButton.isVisible = filterUI != viewModel.baseFilterUI
        viewModel.baseFilterUI = filterUI ?: FilterUI()
    }

    // Метод отвечающий за check box
    fun setCheckedIcon(isChecked: Boolean) {
        with(viewBinding) {
            if (isChecked) {
                withoutSalary.icon = ContextCompat.getDrawable(requireContext(), R.drawable.check_box_on_24px)
            } else {
                withoutSalary.icon = ContextCompat.getDrawable(requireContext(), R.drawable.check_box_off_24px)
            }
        }
    }
}
