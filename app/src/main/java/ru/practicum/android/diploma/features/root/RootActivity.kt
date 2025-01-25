package ru.practicum.android.diploma.features.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding
import ru.practicum.android.diploma.features.search.domain.interactor.VacanciesSearchInteractor

class RootActivity : AppCompatActivity() {

    private val binding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityRootBinding.inflate(layoutInflater)
    }

//    private val interactor by inject<VacanciesSearchInteractor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        lifecycleScope.launch {
//            interactor.getVacancies(text = "fafngkjdsnfkmdsfkfnjdskfb", page = 0, perPage = 5, params = mapOf())
//        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.favouriteVacanciesFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.teamInfoFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.isVisible = true
        binding.view.isVisible = true
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.isGone = true
        binding.view.isGone = true
    }
}
