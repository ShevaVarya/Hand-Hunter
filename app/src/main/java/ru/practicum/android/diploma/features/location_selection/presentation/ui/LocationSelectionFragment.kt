package ru.practicum.android.diploma.features.location_selection.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.databinding.FragmentLocationSelectionBinding

class LocationSelectionFragment : Fragment() {

    private var _binding: FragmentLocationSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }
}
