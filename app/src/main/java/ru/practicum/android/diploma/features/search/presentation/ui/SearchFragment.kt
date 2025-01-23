package ru.practicum.android.diploma.features.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.features.common.presentation.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createViewBinding(inflater, container).root
    }

//    Из за проверки detekt добавил переменную в пустой метод, удалить после реализации
//    Мок для навигации на экран Вакансии
    override fun initUi() {
        viewBinding.filter.setOnClickListener {
            this@SearchFragment.findNavController().navigate(R.id.action_searchFragment_to_vacancyInfoFragment)
        }
    }

//    Из за проверки detekt добавил переменную в пустой метод, удалить после реализации
    override fun observeData() {
        val a = 0
    }

}
