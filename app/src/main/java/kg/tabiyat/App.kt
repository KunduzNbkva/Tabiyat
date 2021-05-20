package kg.tabiyat

import android.app.Application
import kg.tabiyat.data.prefs.SharedPref
import kg.tabiyat.di.appModule
import kg.tabiyat.di.networkModule
import kg.tabiyat.di.repositoryModule
import kg.tabiyat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

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