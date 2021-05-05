package com.example.tabiyat.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tabiyat.R
import com.example.tabiyat.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private var TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_OUT)
    }
}