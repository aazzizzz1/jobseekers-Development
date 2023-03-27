package com.example.jobseeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.jobseeker.databinding.ActivityMainBinding
import com.example.jobseeker.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNav()
    }
private fun setupNav() {
    val navHost =
        supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    val navController = navHost.navController

    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    bottomNavigationView.setupWithNavController(navController)

    val appBarConfig = AppBarConfiguration(
        setOf(
            R.id.id_home_fragment,
            R.id.id_profile_fragment,
            R.id.notesFragment,
            R.id.id_bookmark_fragment,
            R.id.id_search_fragment
        )
    )

    navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        binding.bottomNavigationView.isVisible = appBarConfig.topLevelDestinations.contains(destination.id)
    }

}
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }


}