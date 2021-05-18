package com.example.tabiyat.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
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
import com.example.tabiyat.base.setTitle
import com.example.tabiyat.databinding.ActivityMainBinding
import com.example.tabiyat.intro.IntroActivity
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
    private var isFABOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!App.prefs!!.isIntroShown()) {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.navView.menu.findItem(R.id.placeholder).isEnabled = false
        binding.frame.setOnClickListener {  }
        setContentView(binding.root)
        setToolbar()
        setNavigation()
        onFabClick()
        //changeToolbarItems()
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
            R.id.animalsFragment,
            R.id.infoFragment,
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
                binding.fabAnimal.visibility = View.GONE
                binding.fabPlant.visibility = View.GONE
            } else {
                binding.navView.visibility = View.VISIBLE
                   binding.fab.visibility = View.VISIBLE
                binding.fabAnimal.visibility = View.VISIBLE
                binding.fabPlant.visibility = View.VISIBLE
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

    private fun setFabClick(it: View) {
        it.setOnClickListener {
            closeFABMenu()
            navController.navigate(R.id.addObservationFragment)
        }
    }


    private fun onFabClick() {
        binding.fab.setOnClickListener {
            if (!isFABOpen) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                setFabClick(binding.fabAnimal)
                setFabClick(binding.fabPlant)
                showFABMenu()
            } else {
                closeFABMenu()
            }
        }
    }

    private fun showFABMenu() {
        isFABOpen = true
        binding.fabAnimal.animate().translationY(-160f)
        binding.fabPlant.animate().translationY(-300f)
        binding.fabPlantTxt.animate().translationY(-300f)
        binding.fabAnimalTxt.animate().translationY(-160f)
        binding.fabPlantTxt.visibility = View.VISIBLE
        binding.fabAnimalTxt.visibility = View.VISIBLE
        binding.frame.visibility = View.VISIBLE
    }

    private fun closeFABMenu() {
        binding.frame.visibility = View.INVISIBLE
        isFABOpen = false
        binding.fabAnimal.animate().translationY(0f)
        binding.fabPlant.animate().translationY(0f)
        binding.fabPlantTxt.animate().translationY(0f)
        binding.fabAnimalTxt.animate().translationY(0f)
        binding.fabPlantTxt.visibility = View.INVISIBLE
        binding.fabAnimalTxt.visibility = View.INVISIBLE
    }


}




