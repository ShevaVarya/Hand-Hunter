package ru.practicum.android.diploma.features.selectworkplace.presentation.ui

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
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
import ru.practicum.android.diploma.features.selectlocation.presentation.ui.LocationSelectionFragment
import ru.practicum.android.diploma.features.selectworkplace.presentation.model.WorkplaceLocationState
import ru.practicum.android.diploma.features.selectworkplace.presentation.viewmodel.WorkplaceSelectionViewModel

class WorkplaceSelectionFragment : BaseFragment<FragmentWorkplaceSelectionBinding>() {

    private val viewModel by viewModel<WorkplaceSelectionViewModel>()
    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWorkplaceSelectionBinding {
        return FragmentWorkplaceSelectionBinding.inflate(layoutInflater)
    }

    override fun initUi() {

        viewBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.chooseButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.countryEditText.setOnClickListener {
            startLocationSelectionFragment(true, null)
        }

        viewBinding.regionEditText.setOnClickListener {
            startLocationSelectionFragment(false, null)
        }

        setProhibitionRegionEditText()
        setProhibitionCountryEditText()
        clearCountry()
        clearRegion()
        viewModel.isWorkPlaceShowNeeded()
    }

    override fun observeData() {
        viewModel.getWorkplaceLocationState()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { workplaceLocationState ->
                with(viewBinding) {
                    chooseButton.isVisible = false
                    progressBar.isVisible = false
                    countryEditText.isVisible = false
                    regionEditText.isVisible = false
                    countryTextInput.isEndIconVisible = false
                    regionTextInput.isEndIconVisible = false
                }

                when (workplaceLocationState) {
                    WorkplaceLocationState.Loading -> showProgressBar()
                    WorkplaceLocationState.Init -> showInit()
                    is WorkplaceLocationState.Content -> showSuccess()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showProgressBar() {
        viewBinding.progressBar.isVisible = true
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

            switchForwardClearIconCountry(country.isEmpty())
            switchForwardClearIconRegion(city.isEmpty())

            chooseButton.isVisible =
                (country.isNotEmpty() || city.isNotEmpty())
        }
    }

    private fun setProhibitionRegionEditText() {
        viewBinding.regionEditText.keyListener = null
        viewBinding.regionEditText.isFocusable = false
        viewBinding.regionEditText.isFocusableInTouchMode = false
        viewBinding.regionEditText.isCursorVisible = false
    }

    private fun setProhibitionCountryEditText() {
        viewBinding.countryEditText.keyListener = null
        viewBinding.countryEditText.isFocusable = false
        viewBinding.countryEditText.isFocusableInTouchMode = false
        viewBinding.countryEditText.isCursorVisible = false
    }

    private fun clearCountry() {
        with(viewBinding) {
            countryTextInput.setEndIconOnClickListener {
                if (countryTextInput.editText?.text.isNullOrEmpty().not()) {
                    viewModel.deleteCountryData()
                    countryEditText.text?.clear()
                } else {
                    startLocationSelectionFragment(true, null)
                }
            }
        }
    }

    private fun clearRegion() {
        with(viewBinding) {
            regionTextInput.setEndIconOnClickListener {
                if (regionTextInput.editText?.text.isNullOrEmpty().not()) {
                    viewModel.deleteRegionData()
                    regionEditText.text?.clear()
                } else {
                    startLocationSelectionFragment(false, null)
                }
            }
        }
    }

    private fun switchForwardClearIconCountry(isTextEmpty: Boolean) {
        with(viewBinding) {
            val image = if (isTextEmpty.not()) {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.close_24px
                )
            } else {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.arrow_forward_24px
                )
            }
            countryTextInput.endIconDrawable = image
        }
    }

    private fun switchForwardClearIconRegion(isTextEmpty: Boolean) {
        with(viewBinding) {
            val image = if (isTextEmpty.not()) {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.close_24px
                )
            } else {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.arrow_forward_24px
                )
            }
            regionTextInput.endIconDrawable = image
        }
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

    private fun startLocationSelectionFragment(isCountry: Boolean, countryId: String?) {
        findNavController().navigate(
            R.id.action_workplaceSelectionFragment_to_locationSelectionFragment,
            LocationSelectionFragment.createArgs(
                isCountry,
                countryId
            )
        )
    }

}
