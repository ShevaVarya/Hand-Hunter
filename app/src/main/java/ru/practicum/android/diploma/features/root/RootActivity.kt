package ru.practicum.android.diploma.features.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private val binding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityRootBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.favouriteVacanciesFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.teamInfoFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)
    }

    private fun showBottomNav(){
        binding.bottomNavigationView.isVisible = true
    }

    private fun hideBottomNav(){
        binding.bottomNavigationView.isGone = true
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
