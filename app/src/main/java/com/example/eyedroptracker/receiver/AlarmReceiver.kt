package com.example.eyedroptracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.eyedroptracker.utils.Constants
import io.karn.notify.Notify

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Get the title and message of our notification.
        val title = intent.getStringExtra(Constants.EXTRA_TITLE).toString()
        val message = intent.getStringExtra(Constants.EXTRA_MESSAGE).toString()

        // We want to set an exact time.
        if(intent.action == Constants.ACTION_SET_EXACT) {
            // Build the notification.
            buildNotification(context, title, message)
        }
    }

    // Build a notification.
    private fun buildNotification(context: Context, title: String, message: String) {
        // Call our notification using the Notify Plugin.
        Notify
            .with(context)
            .content {
                this.title = title
                text = message
            }
            .show()
    }
}