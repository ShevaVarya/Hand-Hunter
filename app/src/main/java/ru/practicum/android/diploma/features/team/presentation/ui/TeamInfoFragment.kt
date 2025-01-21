package ru.practicum.android.diploma.features.team.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentTeamInfoBinding
import ru.practicum.android.diploma.features.team.presentation.ui.adapter.MemberAdapter

class TeamInfoFragment : Fragment() {

    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.teamRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.teamRecyclerView.adapter = MemberAdapter(getMembersList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMembersList(): List<String> {
        return listOf(
            requireContext().getString(R.string.dev_name1),
            requireContext().getString(R.string.dev_name2),
            requireContext().getString(R.string.dev_name3),
            requireContext().getString(R.string.dev_name4),
            requireContext().getString(R.string.dev_name5)
        )
    }
}
