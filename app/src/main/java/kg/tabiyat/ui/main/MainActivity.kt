package kg.tabiyat.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.*
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import kg.tabiyat.App
import kg.tabiyat.R
import kg.tabiyat.base.setTitle
import kg.tabiyat.databinding.ActivityMainBinding
import kg.tabiyat.intro.IntroActivity
import java.util.*
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
    private var navBuilder:NavOptions.Builder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.dark_gray)

        FirebaseApp.initializeApp(this)
        if (!App.prefs!!.isIntroShown()) {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.navView.menu.findItem(R.id.placeholder).isEnabled = false
        binding.frame.setOnClickListener { }
        setContentView(binding.root)
        setToolbar()
        setNavigation()
        onFabClick()
        navBuilder = NavOptions.Builder()
        navBuilder!!.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)
    }

    companion object {
        @SuppressLint("ServiceCast")
        fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
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
            R.id.cardDetailFragment,
            R.id.animalsFragment,
            R.id.infoFragment,
            R.id.projectInfoFragment,
            R.id.accountFragment,
            R.id.menuSettings,
            R.id.observationsFragment,
            R.id.menuNotifications,
            R.id.addObservationFragment,
            R.id.cardObservationFragment,
            R.id.choosePlantFragment,
            R.id.locationMapFragment,
            R.id.addAnimalObsrvFragment,
            R.id.chooseAnimalFragment,
            R.id.newsDetailFragment,
            R.id.newsFragment
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

    private fun changeToolbarItems(destinationId: Int) {
        invalidateOptionsMenu()
        when (destinationId) {
            R.id.navigation_profile -> {
                menuItemSettings.isVisible = true
                menuItemNotifications.isVisible = false
            }
            R.id.cardDetailFragment -> {
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
            R.id.addAnimalObsrvFragment -> {
                menuItemNotifications.isVisible = false
            }
            R.id.addObservationFragment -> {
                menuItemNotifications.isVisible = false
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        changeToolbarItems(destinationId = destinationId)
        return super.onPrepareOptionsMenu(menu)
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
        when (it) {
            binding.fabAnimal -> {
                it.setOnClickListener {
                    openAddAnimal()
                }
            }
            binding.fabAnimalTxt -> {
                it.setOnClickListener {
                    openAddAnimal()
                }
            }
            binding.fabPlant -> {
                it.setOnClickListener {
                    openAddPlant()
                }
            }
            binding.fabPlantTxt -> {
                it.setOnClickListener {
                    openAddPlant()
                }
            }
        }
    }

    private fun openAddAnimal() {
        closeFABMenu()
        navController.navigate(R.id.addAnimalObsrvFragment,null,navBuilder!!.build())
    }

    private fun openAddPlant() {
        closeFABMenu()
        navController.navigate(R.id.addObservationFragment,null,navBuilder!!.build())
    }


    private fun onFabClick() {
        binding.fab.setOnClickListener {
            if (!isFABOpen) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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
        setFabClick(binding.fabAnimal)
        setFabClick(binding.fabPlant)
        setFabClick(binding.fabAnimalTxt)
        setFabClick(binding.fabPlantTxt)
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

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        applicationContext.resources.updateConfiguration(
            config,
            applicationContext.resources.displayMetrics
        )
        App.prefs!!.saveLang(lang)
    }

    override fun onResume() {
        loadLocaleLang()
        super.onResume()
    }

    private fun loadLocaleLang() {
        val language: String = App.prefs!!.lanquage!!
        if (language != null) {
            setLocale(language)
        }
    }


}




