package ru.practicum.android.diploma.features.filters.presentation.ui.workplace

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentWorkplaceSelectionBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment
import ru.practicum.android.diploma.features.filters.presentation.model.state.WorkplaceLocationState
import ru.practicum.android.diploma.features.filters.presentation.ui.location.LocationSelectionFragment

class WorkplaceSelectionFragment : BaseFragment<FragmentWorkplaceSelectionBinding>() {

    private val viewModel by viewModel<WorkplaceSelectionViewModel>()
    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWorkplaceSelectionBinding {
        return FragmentWorkplaceSelectionBinding.inflate(layoutInflater)
    }

    override fun initUi() {
        viewBinding.toolbar.setNavigationOnClickListener {
            viewModel.resetAllChanges()
            findNavController().popBackStack()
        }

        viewBinding.chooseButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.countryEditText.setOnClickListener {
            startLocationSelectionFragment(true)
        }

        viewBinding.regionEditText.setOnClickListener {
            startLocationSelectionFragment(false)
        }

        setProhibitionEditText(viewBinding.countryEditText)
        setProhibitionEditText(viewBinding.regionEditText)
        clearField(
            viewBinding.countryTextInput,
            viewBinding.countryEditText,
            { viewModel.deleteCountryData() },
            { startLocationSelectionFragment(it) },
            true
        )
        clearField(
            viewBinding.regionTextInput,
            viewBinding.regionEditText,
            { viewModel.deleteRegionData() },
            { startLocationSelectionFragment(it) },
            false
        )
        viewModel.isWorkPlaceShowNeeded()
    }

    override fun observeData() {
        viewModel.getWorkplaceLocationState()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { workplaceLocationState ->
                with(viewBinding) {
                    chooseButton.isVisible = false
                    countryEditText.isVisible = false
                    regionEditText.isVisible = false
                    countryTextInput.isEndIconVisible = false
                    regionTextInput.isEndIconVisible = false
                }

                when (workplaceLocationState) {
                    WorkplaceLocationState.Init -> showInit()
                    is WorkplaceLocationState.Content -> showSuccess()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showInit() {
        with(viewBinding) {
            countryEditText.isVisible = true
            regionEditText.isVisible = true
            countryTextInput.isEndIconVisible = true
            regionTextInput.isEndIconVisible = true
        }
    }

    private fun showSuccess() {
        val location = viewModel.getLocation()
        val country = location.country
        val city = location.city
        with(viewBinding) {
            countryEditText.setText(country)
            regionEditText.setText(city)

            renderEditTextColor(countryTextInput, country)
            renderEditTextColor(regionTextInput, city)

            countryEditText.isVisible = true
            regionEditText.isVisible = true
            countryTextInput.isEndIconVisible = true
            regionTextInput.isEndIconVisible = true

            switchForwardClearIcon(viewBinding.countryTextInput, country.isEmpty())
            switchForwardClearIcon(viewBinding.regionTextInput, city.isEmpty())

            chooseButton.isVisible = country.isNotEmpty() || city.isNotEmpty()
        }
    }

    private fun setProhibitionEditText(editText: EditText) {
        editText.keyListener = null
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isCursorVisible = false
    }

    private fun clearField(
        textInputLayout: TextInputLayout,
        editText: EditText,
        deleteAction: () -> Unit,
        startSelection: (Boolean) -> Unit,
        isCountry: Boolean
    ) {
        textInputLayout.setEndIconOnClickListener {
            if (editText.text.isNullOrEmpty().not()) {
                deleteAction()
                editText.text?.clear()
            } else {
                startSelection(isCountry)
            }
        }
    }

    private fun switchForwardClearIcon(textInputLayout: TextInputLayout, isTextEmpty: Boolean) {
        val image = if (isTextEmpty.not()) {
            ContextCompat.getDrawable(requireContext(), R.drawable.close_24px)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.arrow_forward_24px)
        }
        textInputLayout.endIconDrawable = image
    }

    private fun renderEditTextColor(view: TextInputLayout, text: CharSequence?) {
        val typedValue = TypedValue()
        if (!text.isNullOrEmpty()) {
            requireContext().theme.resolveAttribute(R.attr.mainEditTextColor, typedValue, true)
            view.defaultHintTextColor = ColorStateList.valueOf(typedValue.data)
        } else {
            requireContext().theme.resolveAttribute(R.attr.hintEditTextColor, typedValue, true)
            view.defaultHintTextColor = ColorStateList.valueOf(typedValue.data)
        }
    }

    private fun startLocationSelectionFragment(isCountry: Boolean) {
        findNavController().navigate(
            R.id.action_workplaceSelectionFragment_to_locationSelectionFragment,
            LocationSelectionFragment.createArgs(isCountry)
        )
    }

}
