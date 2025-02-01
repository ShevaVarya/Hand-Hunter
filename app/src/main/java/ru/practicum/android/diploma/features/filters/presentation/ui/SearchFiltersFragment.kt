package ru.practicum.android.diploma.features.filters.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchFiltersBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.filters.presentation.model.FilterUI
import ru.practicum.android.diploma.features.filters.presentation.viewmodel.SearchFilterViewModel

class SearchFiltersFragment : BaseFragment<FragmentSearchFiltersBinding>() {

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchFiltersBinding {
        return FragmentSearchFiltersBinding.inflate(inflater, container, false)
    }

    private val viewModel: SearchFilterViewModel by viewModel<SearchFilterViewModel>()

    override fun initUi() {
        initializeViews()
    }

    override fun observeData() {
        viewModel.getIndustries()

//        viewModel.currentFilter.observe(viewLifecycleOwner) { filter ->
//            currentFilter = filter
//            processFilterResult(filter)
//            viewModel.setChosenCountry(filter.country)
//            viewModel.setChosenRegion(filter.region)
//            setupClearButton(filter.country, binding.placeOfWork) { viewModel.clearPlaceOfWork() }
//            setupClearButton(filter.industry, binding.industry) { viewModel.setIndustry(null) }
//        }
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
                viewModel.saveFilter()
                viewModel.retrySearchQueryWithFilterSearch()
                findNavController().navigateUp()
            }

            salaryEditText.doOnTextChanged { s, _, _, _ ->
                setButtonVisibility(viewModel.currentFilterUI)
                viewModel.salaryEnterTextChanged(s)
                salaryEnterClearIcon(s)
            }

//        binding.withoutSalary.setOnClickListener {
//            binding.withoutSalary.setOnClickListener { viewModel.setOnlyWithSalary(binding.withoutSalary.isChecked) }
//        }
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
//    private fun <T> setupClearButton(item: T?, til: TextInputLayout, action: () -> Unit) {
//        if (item != null) {
//            til.setEndIconDrawable(R.drawable.close_24px)
//            til.setEndIconOnClickListener {
//                action.invoke()
//                til.setEndIconOnClickListener(null)
//            }
//        } else {
//            til.setEndIconDrawable(R.drawable.arrow_forward_24px)
//            til.isEndIconVisible = false
//            til.isEndIconVisible = true
//        }
//    }

    // Метод для заполнения полей, иначе если значение по умолчанию - то поля очищаются
//    private fun processFilterResult(filter: Filter) {
//        setButtonVisibility(filter)
//        if (!filter.isDefault) {
//            processArea(filter.country, filter.region)
//            binding.industryEnter.setText(filter.industry?.name ?: "")
//            binding.withoutSalary.isChecked = filter.onlyWithSalary
//            val newSalary = filter.salary
//            if (newSalary != oldSalary) {
//                binding.salaryEnter.setText(newSalary.toString())
//            }
//        } else {
//            binding.placeOfWorkEnter.text = null
//            binding.industryEnter.text = null
//            binding.withoutSalary.isChecked = false
//            binding.salaryEnter.text = null
//        }
//        setCheckedIcon(filter.onlyWithSalary)
//    }

//    Метод для сохранения страны и региона в "Место работы"
//    private fun processArea(country: CountryUI?, region: RegionUI?) {
//        var result = ""
//        if (country != null) {
//            result += country.name
//        }
//        if (region != null) {
//            result += ", ${region.name}"
//        }
//        binding.placeOfWorkEnter.setText(result)
//    }

    // Метод отвечающий за отображение кнопок "Применить" "Сбросить"
    private fun setButtonVisibility(filterUI: FilterUI) {
//        binding.resetButton.isVisible = !filter.isDefault
//        binding.acceptButton.isVisible = filter != viewModel.latestSearchFilter
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
