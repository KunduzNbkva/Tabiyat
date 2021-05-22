package kg.tabiyat.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kg.tabiyat.R


object NotificationBuilder {

    const val ACTIVITY_CLASSPATH = "kg.tabiyat.ui.main.MainActivity"

    fun createNotification(context: Context, data: NotificationData) {
        val builder = setNotificationBuilder(context, data)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(data.id, builder.build())
    }

    private fun setNotificationBuilder(
        context: Context,
        data: NotificationData
    ): NotificationCompat.Builder {
        val channelId = "${context.packageName}-Tabiyat"
        return NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_leaf)
            setContentTitle(data.title)
            setContentText(data.shortMessage)
            setAutoCancel(data.autoCancel)
            setStyle(NotificationCompat.BigTextStyle().bigText(data.expandedText))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(data.autoCancel)
            setContentIntent(createPendingIntent(context))
            setGroup("kg.tabiyat.notification.NotificationBuilder")
            setGroupSummary(true)
        }
    }

    private fun createPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, Class.forName(ACTIVITY_CLASSPATH))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}