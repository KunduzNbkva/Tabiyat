package kg.tabiyat.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object NotificationChannelBuilder {

    fun createNotificationChannel(
        context: Context,
        importance: Int,
        showBadge: Boolean,
        name: String,
        desc: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = "${context.packageName}-$name"
            val channel = prepareChannel(id, name, importance)
            channel.setChannelSettings(desc, showBadge)
            registerChannel(context, channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerChannel(context: Context, channel: NotificationChannel) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun NotificationChannel.setChannelSettings(desc: String, showBadge: Boolean) {
        this.apply {
            description = desc
            setShowBadge(showBadge)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun prepareChannel(id: String, name: String, importance: Int): NotificationChannel {
        return NotificationChannel(id, name, importance)
    }
}