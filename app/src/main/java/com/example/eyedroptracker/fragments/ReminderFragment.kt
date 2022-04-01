package com.example.eyedroptracker.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eyedroptracker.R
import com.example.eyedroptracker.models.User
import com.example.eyedroptracker.service.AlarmService
import com.example.eyedroptracker.utils.AppDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_reminder.*
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.lang.String.format
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.concurrent.atomic.AtomicInteger

class ReminderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alarmService = AlarmService(requireContext())
        val timePicker = view.findViewById<TimePicker>(R.id.datePicker1)
        var calendar = Calendar.getInstance()

        // Save Button is clicked.
        save_reminder.setOnClickListener{
            // Get the time that was Input by the User.
            val timeInMilliseconds = getSetTimeInMilliseconds(calendar, timePicker)

            // TODO: - This information needs to be saved in order to delete notifications.
            // Generate a Request Code.
            val seed = AtomicInteger()
            val requestCodeID = seed.getAndIncrement() + System.currentTimeMillis().toInt()

            // TODO: - The medication should be within this message.
            // Set the alarm.
            alarmService.setExactAlarm(
                timeInMilliseconds,
                "Medication Reminder",
                "Please take your medication.",
                requestCodeID)

            // Show that we set our reminder.
            Snackbar.make(view,"Reminder has been set", Snackbar.LENGTH_SHORT)
                .show();
        }

    }

    // Get the time in milliseconds by our two inputs fields.
    private fun getSetTimeInMilliseconds(calendar: Calendar, timePicker: TimePicker): Long{
        // Formatter we must fit to combine our times from calendar and clock.
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

        // Get the current date.
        var dateInMillisecond = Calendar.getInstance().time
        val day = DateFormat.format("yyyy-MM-dd", dateInMillisecond).toString()

        // Get hour.
        var hour = timePicker.currentHour.toString()
        if(hour.length < 2)
        {
            hour = "0$hour"
        }

        // Get the minute
        var minute = timePicker.currentMinute.toString()
        if(minute.length < 2)
        {
            minute = "0$minute"
        }

        // Get the combination to fit our formatter.
        val dateStr = day+"T"+hour+":"+minute+":"+"00Z"

        // Return combined times in milliseconds.
        return formatter.parse(dateStr).time
    }
}