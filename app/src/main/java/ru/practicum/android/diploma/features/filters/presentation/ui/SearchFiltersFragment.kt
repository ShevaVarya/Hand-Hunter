package ru.practicum.android.diploma.features.filters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchFiltersBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.filters.presentation.model.Filter
import ru.practicum.android.diploma.features.filters.presentation.viewmodel.SearchFilterViewModel

class SearchFiltersFragment : BaseFragment<FragmentSearchFiltersBinding>() {

    private var _binding: FragmentSearchFiltersBinding? = null
    private val binding get() = _binding!!
    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchFiltersBinding {
        return FragmentSearchFiltersBinding.inflate(inflater, container, false)
    }

    private val viewModel: SearchFilterViewModel by viewModel<SearchFilterViewModel>()

    private var currentFilter = Filter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        binding.salaryFrame.requestFocus()

        binding.placeOfWorkEnter.setOnClickListener {
            // Навигация к выбору места работы(Страна, Регион)
        }

        binding.industryEnter.setOnClickListener {
            // Навигация к выбору отрасли
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.resetButton.setOnClickListener {
            viewModel.clearFilter()
        }

        binding.acceptButton.setOnClickListener {
            viewModel.saveFilter()
            viewModel.retrySearchQueryWithFilterSearch()
            findNavController().navigateUp()
        }

        binding.salaryEnter.doOnTextChanged { s, _, _, _ ->
            setButtonVisibility(currentFilter)
            viewModel.salaryEnterTextChanged(s, binding.salaryFrame)
        }

//        binding.withoutSalary.setOnClickListener {
//            binding.withoutSalary.setOnClickListener { viewModel.setOnlyWithSalary(binding.withoutSalary.isChecked) }
//        }
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
    private fun setButtonVisibility(filter: Filter) {
//        binding.resetButton.isVisible = !filter.isDefault
//        binding.acceptButton.isVisible = filter != viewModel.latestSearchFilter
    }

    // Метод отвечающий за check box
    fun setCheckedIcon(isChecked: Boolean) {
        if (isChecked) {
            binding.withoutSalary.icon = ContextCompat.getDrawable(requireContext(), R.drawable.check_box_on_24px)
        } else {
            binding.withoutSalary.icon = ContextCompat.getDrawable(requireContext(), R.drawable.check_box_off_24px)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
