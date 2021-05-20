package kg.tabiyat.intro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kg.tabiyat.R
import kg.tabiyat.auth.LoginActivity
import kg.tabiyat.databinding.ActivityIntroBinding


class IntroActivity : FragmentActivity() {
    private lateinit var introBinding: ActivityIntroBinding
    private lateinit var viewPager: ViewPager
    private var pos: Int? = null
    private lateinit var btnNext: Button
    private lateinit var btnSkip: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        introBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding.root)
        btnNext = introBinding.introNextBtn
        btnSkip = introBinding.introSkipBtn

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagersAdapter(supportFragmentManager)
        introBinding.dotsIndicator.setViewPager(viewPager)
        setViewPager()

        btnSkip.setOnClickListener { onSkipButton() }
        btnNext.setOnClickListener { onNextButton() }

        saveIsIntroShown()
    }

    private fun setViewPager() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                pos = position
                when (pos) {
                    0 -> {
                        btnSkip.visibility = View.VISIBLE
                        introBinding.introNextBtn.setOnClickListener {
                            onNextButton()
                        }
                    }
                    1 -> {
                        btnSkip.visibility = View.VISIBLE
                        btnNext.setOnClickListener { onNextButton() }
                    }
                    2 -> {
                        btnSkip.visibility = View.GONE
                        btnNext.setOnClickListener { onSkipButton() }
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }


    fun onSkipButton() {
        _root_ide_package_.kg.tabiyat.App.prefs!!.saveIsIntroShown()
        //TODO  saveIsIntroShown()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun onNextButton() {
        viewPager.setCurrentItem(getItem(+1), true)
    }

    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }


    private inner class ViewPagersAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("pos", position)
            val fragment = ViewPagerFragment()
            fragment.arguments = bundle
            return fragment
        }

        override fun getCount(): Int {
            return 3
        }
    }

    private fun saveIsIntroShown() {
        val preferences: SharedPreferences =
            this.getSharedPreferences("settings", Context.MODE_PRIVATE)
        preferences.edit().putBoolean("isShown", true).apply()
    }
}