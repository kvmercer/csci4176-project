package com.example.eyedroptracker.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eyedroptracker.dao.MedicationDao
import com.example.eyedroptracker.dao.ReminderDao
import com.example.eyedroptracker.dao.UserDao
import com.example.eyedroptracker.models.Medication
import com.example.eyedroptracker.models.Reminder
import com.example.eyedroptracker.models.User


@Database(entities = [User::class, Reminder::class, Medication::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reminderDao(): ReminderDao
    abstract  fun medicationDao(): MedicationDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        operator fun invoke(context: Context) = instance?: synchronized(Any()) {
            instance?: createDB(context).also {
                it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appDB"
        ).build()
    }
}