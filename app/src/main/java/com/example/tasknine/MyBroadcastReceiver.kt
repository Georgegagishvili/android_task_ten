package com.example.tasknine
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager


class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        WorkManager.getInstance().cancelAllWork()
        NotificationManagerCompat.from(context).cancelAll()
        Toast.makeText(context, "Notification Service Stopped", Toast.LENGTH_SHORT).show()
    }
}