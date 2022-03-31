package com.example.eyedroptracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.eyedroptracker.utils.Constants
import com.example.eyedroptracker.service.AlarmService
import android.text.format.DateFormat
import io.karn.notify.Notify

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Get the time of the exact alarm time.
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)

        // Title for our Notification.
        val title = intent.getStringExtra(Constants.EXTRA_TITLE).toString()

        // Message for our Notification.
        val message = intent.getStringExtra(Constants.EXTRA_MESSAGE).toString()

        // If we have an action.
        when (intent.action) {
            // The action is to set an exact time.
            Constants.ACTION_SET_EXACT -> {
                // Build our notification.
                buildNotification(context, title, message)
            }
        }
    }

    // Build a notification with our Notify plugin.
    private fun buildNotification(context: Context, title: String, message: String) {
        // Call our notification.
        Notify
            .with(context)
            .content {
                this.title = title
                text = message
            }
            .show()
    }
}