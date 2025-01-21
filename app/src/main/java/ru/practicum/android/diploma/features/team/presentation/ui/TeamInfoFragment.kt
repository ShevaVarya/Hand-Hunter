package ru.practicum.android.diploma.features.team.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentTeamInfoBinding

class TeamInfoFragment : Fragment() {

    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var adapter: MemberAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.teamRecyclerView
        adapter = MemberAdapter(
            listOf(
                requireContext().getString(R.string.dev_name1),
                requireContext().getString(R.string.dev_name2),
                requireContext().getString(R.string.dev_name3),
                requireContext().getString(R.string.dev_name4),
                requireContext().getString(R.string.dev_name5)
            )
        )
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recyclerView = null
        adapter = null
    }
}
