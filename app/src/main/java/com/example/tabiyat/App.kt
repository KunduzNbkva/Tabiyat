package com.example.tabiyat

import android.app.Application
import com.example.tabiyat.data.prefs.SharedPref
import com.example.tabiyat.di.appModule
import com.example.tabiyat.di.networkModule
import com.example.tabiyat.di.repositoryModule
import com.example.tabiyat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App: Application(){

    companion object {
        var prefs: SharedPref? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPref(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, networkModule, appModule))
        }
    }



}