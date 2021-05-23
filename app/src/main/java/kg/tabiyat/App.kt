package kg.tabiyat

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import kg.tabiyat.data.prefs.SharedPref
import kg.tabiyat.data.local.db.AppDatabase
import kg.tabiyat.di.*
import kg.tabiyat.notification.NotificationChannelBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    lateinit var db: AppDatabase
    private lateinit var notificationChannelBuilder: NotificationChannelBuilder

    companion object {
        var prefs: SharedPref? = null
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        notificationChannelBuilder = NotificationChannelBuilder
        prefs = SharedPref(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, networkModule, appModule, localModule))
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        notificationChannelBuilder.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_HIGH,
            false,
            "Tabiyat",
            "Notification from Tabiyat"
        )
    }

}