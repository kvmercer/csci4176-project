package com.example.eyedroptracker.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.content.Intent
import com.example.eyedroptracker.receiver.AlarmReceiver
import com.example.eyedroptracker.utils.Constants
import com.example.eyedroptracker.utils.RandomIntUtil
import android.os.Bundle

// TODO: - Need to cite this https://www.youtube.com/watch?v=D0VpASTpgmw&ab_channel=FoodieDev
class AlarmService (private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    // Set the exact alarm in milliseconds.
    fun setExactAlarm(timeInMillis: Long) {
        setAlarm(
            timeInMillis,
            getPendingIntent(
                getIntent().apply {
                    // Set our action to exact time.
                    action = Constants.ACTION_SET_EXACT
                    // Our notification information.
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                    putExtra(Constants.EXTRA_TITLE, "This is a test Title")
                    putExtra(Constants.EXTRA_MESSAGE, "This is a test Message")
                }
            )
        )
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
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    // TODO: - Need a function to cancel notification.

    // Get the Intent.
    private fun getIntent():Intent = Intent(context, AlarmReceiver::class.java)

    // Get the pending Intent.
    private fun getPendingIntent(intent: Intent) =
        // Send a pendingIntent to our AlarmReceiver.
        PendingIntent.getBroadcast(
            context,
            RandomIntUtil.getRandomInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
}