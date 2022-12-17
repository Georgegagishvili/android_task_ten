package com.example.tasknine
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.work.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        findViewById<Button>(R.id.notification_button).setOnClickListener {
            createWorkRequest()
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel)
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("default_notification_channel", name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("RestrictedApi", "VisibleForTests")
    private fun createWorkRequest() {

        val myWorkRequest =
            OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(5, java.util.concurrent.TimeUnit.SECONDS)
                .build()

        val workManager = WorkManager.getInstance(this)

        workManager.enqueue(myWorkRequest)

    }
}