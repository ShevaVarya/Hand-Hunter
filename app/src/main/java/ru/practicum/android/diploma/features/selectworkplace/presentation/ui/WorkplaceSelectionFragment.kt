package ru.practicum.android.diploma.features.selectworkplace.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        viewBinding.countryTextInput.setOnClickListener {
            findNavController().navigate(
                R.id.action_workplaceSelectionFragment_to_locationSelectionFragment,
                LocationSelectionFragment.createArgs(true)
            )
        }

        viewBinding.regionEditText.setOnClickListener {
            findNavController().navigate(
                R.id.action_workplaceSelectionFragment_to_locationSelectionFragment,
                LocationSelectionFragment.createArgs(false)
            )
        }
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
                    WorkplaceLocationState.Error -> Unit
                    WorkplaceLocationState.Loading -> showProgressBar()
                    is WorkplaceLocationState.Success -> {
                        val location = workplaceLocationState.location
                        showSuccess(location.country.name, location.city.name)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showProgressBar() {
        viewBinding.progressBar.isVisible = true
    }

    private fun showSuccess(country: String, city: String) {
        with(viewBinding) {
            countryEditText.setText(country)
            regionEditText.setText(city)
            countryEditText.isVisible = true
            regionEditText.isVisible = true
            countryTextInput.isEndIconVisible = true
            regionTextInput.isEndIconVisible = true
            chooseButton.isVisible = (countryEditText.text.isNullOrEmpty() || regionEditText.text.isNullOrEmpty()).not()
        }
    }

}
