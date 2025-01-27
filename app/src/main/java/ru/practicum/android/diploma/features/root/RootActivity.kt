package ru.practicum.android.diploma.features.root

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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
            when (destination.id) {
                R.id.favouriteVacanciesFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.teamInfoFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            window?.let {
                WindowCompat.setDecorFitsSystemWindows(it, true)
                WindowInsetsControllerCompat(it, binding.root).isAppearanceLightStatusBars =
                    nightModeFlags == Configuration.UI_MODE_NIGHT_NO
            }
        } else {
            var flags: Int = binding.root.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            binding.root.systemUiVisibility = flags
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
