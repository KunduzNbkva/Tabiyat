package kg.tabiyat.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import kg.tabiyat.R;


public class FirebaseMessagingReceiver extends FirebaseMessagingService {
    final String CHANNEL_ID = "my_channel_01";
    final String CHANNEL_NAME = "Tabiyat";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Функция ниже для бесшумного уведомления (уведомления без звука и без содержания)
//            showNotification(remoteMessage.getData());

    }

    private void showNotification(Map<String, String> data) throws ClassNotFoundException {
        String title = data.get("title");
        String body = data.get("body");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Tabiyat");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 800, 500, 800});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_leaf);
//        Intent intent = new Intent(this, StartFromNotificationActivity.class);
//        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(this, 100,  intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(bm)
                .setSmallIcon(R.drawable.ic_leaf)
                .setContentTitle(title)
                .setContentIntent(notificationClickIntent())
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
    }

    private void showNotification(String title, String body) throws ClassNotFoundException {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Tabiyat");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{0, 600, 500, 600});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//        Intent intent = new Intent(this, StartFromNotificationActivity.class);
//        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(this, 100,  intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_leaf);
        notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(bm)
                .setSmallIcon(R.drawable.ic_leaf)
                .setContentTitle(title)
                .setContentIntent(notificationClickIntent())
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("---", "Firebase Token: " + s);
    }

    private PendingIntent notificationClickIntent() throws ClassNotFoundException {
        Intent intent = new Intent(this, Class.forName("kg.tabiyat.ui.main.MainActivity"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.putExtra(CHANGE_WORKSPACE, SHOW_NOTIFICATIONS);
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
