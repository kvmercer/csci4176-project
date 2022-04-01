package com.example.eyedroptracker.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.content.Intent
import com.example.eyedroptracker.receiver.AlarmReceiver
import com.example.eyedroptracker.utils.Constants

class AlarmService (private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    // Set an alarm that will execute a notification given:
    // - Time in milliseconds the notification will be executed.
    // - Title of the notification.
    // - Message of the notification.
    // - The request code so we can delete the notification later.
    fun setExactAlarm(timeInMillis: Long, title: String, message: String, requestCode: Int) {
        setAlarm(
            timeInMillis,
            getPendingIntent(
                getIntent().apply {
                    // Set our action to exact time.
                    action = Constants.ACTION_SET_EXACT

                    // Our notification information.
                    putExtra(Constants.EXTRA_TITLE, title)
                    putExtra(Constants.EXTRA_MESSAGE, message)
                },
                requestCode
            )
        )
    }

    // Delete an alarm that will execute a notification given:
    // - Time in milliseconds the notification will be executed.
    // - Title of the notification.
    // - Message of the notification.
    // - The request code assigned.
    fun cancelExactAlarm(timeInMillis: Long, title: String, message: String, requestCode: Int) {
        // Recreate our pending intent.
        var pendingIntent = getPendingIntent(
            getIntent().apply {
                // Set our action to exact time.
                action = Constants.ACTION_SET_EXACT

                // Our notification information.
                putExtra(Constants.EXTRA_TITLE, title)
                putExtra(Constants.EXTRA_MESSAGE, message)
            },
            requestCode
        )

        // Cancel it.
        alarmManager?.cancel(pendingIntent)
    }

    // Set the alarm with the time in milliseconds and our PendingIntent to be executed.
    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
            else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    // Get the Intent.
    private fun getIntent():Intent = Intent(context, AlarmReceiver::class.java)

    // Get the Pending Intent.
    private fun getPendingIntent(intent: Intent, requestCode: Int) =
        PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
}