package com.example.eyedroptracker.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eyedroptracker.dao.MedicationDao
import com.example.eyedroptracker.dao.ReminderDao
import com.example.eyedroptracker.dao.UserDao
import com.example.eyedroptracker.models.Medication
import com.example.eyedroptracker.models.Reminder
import com.example.eyedroptracker.models.User


@Database(entities = [User::class, Reminder::class, Medication::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reminderDao(): ReminderDao
    abstract  fun medicationDao(): MedicationDao
}