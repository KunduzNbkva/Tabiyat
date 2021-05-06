package com.example.tabiyat.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tabiyat.App
import com.example.tabiyat.R
import com.example.tabiyat.databinding.ActivityMainBinding
import com.example.tabiyat.intro.IntroActivity
import com.example.tabiyat.ui.setTitle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var menuItemNotifications: MenuItem
    private lateinit var menuItemSettings: MenuItem
    private lateinit var menuItemForward: MenuItem
    private var destinationId by Delegates.notNull<Int>()
    private lateinit var navView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!App.prefs!!.isIntroShown()) {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.navView.menu.findItem(R.id.placeholder).isEnabled = false
        setContentView(binding.root)
        setToolbar()
        setNavigation()
        setFabClick()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menuItemNotifications = menu.findItem(R.id.menuNotifications)
        menuItemSettings = menu.findItem(R.id.menuSettings)
        menuItemForward = menu.findItem(R.id.menuForward)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }


    private fun setNavigation() {
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tabiyat,
                R.id.navigation_map,
                R.id.navigation_liked,
                R.id.navigation_profile
            )
        )
        Navigation.setViewNavController(binding.fab, navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        onDestinationChanged()
    }

    private fun onDestinationChanged() {
        val mainDestinations =
            setOf(
                R.id.navigation_tabiyat,
                R.id.navigation_map,
                R.id.navigation_liked,
                R.id.navigation_profile
            )

        val hideNavDestinations = setOf(
            R.id.plantsFragment,
            R.id.plantsDetailFragment,
            R.id.projectInfoFragment,
            R.id.accountFragment,
            R.id.menuSettings,
            R.id.settings_account,
            R.id.observationsFragment,
            R.id.menuNotifications,
            R.id.addObservationFragment,
            R.id.cardObservationFragment
        )

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            setToolbar()
            destinationId = destination.id
            binding.toolbar.setTitle(destination.label, binding.toolbarTxt, arguments)
            if (mainDestinations.contains(destination.id)) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }
            if (hideNavDestinations.contains(destination.id)) {
                binding.navView.visibility = View.GONE
                binding.fab.visibility = View.GONE
            } else {
                binding.navView.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
            }
        }
    }


    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun changeToolbarItems(destinationId: Int) {
        invalidateOptionsMenu()
        when (destinationId) {
            R.id.navigation_profile -> {
                menuItemSettings.isVisible = true
                menuItemNotifications.isVisible = false
            }
            R.id.plantsDetailFragment -> {
                menuItemForward.isVisible = true
                menuItemSettings.isVisible = false
                menuItemNotifications.isVisible = false
            }
            R.id.menuNotifications -> menuItemNotifications.isVisible = false

            R.id.menuSettings -> {
                menuItemSettings.isVisible = false
                menuItemNotifications.isVisible = false
            }
            R.id.accountFragment -> {
                menuItemForward.isVisible = false
                menuItemSettings.isVisible = false
                menuItemNotifications.isVisible = false
            }
            R.id.projectInfoFragment -> {
                menuItemNotifications.isVisible = false
            }
            R.id.observationsFragment -> {
                menuItemNotifications.isVisible = false

            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        changeToolbarItems(destinationId = destinationId)
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setFabClick() {
        binding.fab.setOnClickListener {
            it.let {
                Navigation.findNavController(it).navigate(R.id.addObservationFragment)
            }
        }
    }

}



