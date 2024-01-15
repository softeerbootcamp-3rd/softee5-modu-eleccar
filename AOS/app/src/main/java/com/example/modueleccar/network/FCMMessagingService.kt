package com.example.modueleccar.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.modueleccar.R
import com.example.modueleccar.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("onNewToken", token)
        super.onNewToken(token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val body = remoteMessage.notification?.body


        if (body != null) {
            Log.d("FCM", "Notification Message Body: $body")
            Log.d("FCM eventId", remoteMessage.data["eventId"].toString())
            // 푸시 알림을 생성하고 표시
            showNotification(body, remoteMessage.data["eventId"]!!.toInt())
        }
    }

    private fun showNotification(message: String, eventId: Int) {
        val channelId = "119"
        val channelName = "Default Channel"
        val notificationId = 1
        Log.d("showNotification", eventId.toString())
        // Notification Manager 생성
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel 생성 (Android 8.0 이상에서 필요)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_MAX
            )
            notificationManager.createNotificationChannel(channel)
        }

        val acceptIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("test", "testvalue")
        }
        acceptIntent.putExtra("eventId", eventId)

        val acceptPendingIntent = PendingIntent.getActivity(
            this,
            0,
            acceptIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Notification Builder 생성
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.icon_car_bab)
            .setContentTitle("카밥 알림")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(acceptPendingIntent)

        // Notification 표시
        notificationManager.notify(notificationId, builder.build())
    }
}