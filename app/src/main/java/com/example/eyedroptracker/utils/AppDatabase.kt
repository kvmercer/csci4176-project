package com.example.eyedroptracker.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eyedroptracker.dao.MedicationDao
import com.example.eyedroptracker.dao.ReminderDao
import com.example.eyedroptracker.dao.UserDao
import com.example.eyedroptracker.models.Medication
import com.example.eyedroptracker.models.Reminder
import com.example.eyedroptracker.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


@Database(entities = [User::class, Reminder::class, Medication::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reminderDao(): ReminderDao
    abstract  fun medicationDao(): MedicationDao

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var reminderDao = database.reminderDao()
                    var medicationDao = database.medicationDao()

                    reminderDao.deleteAll()
                    medicationDao.deleteAll()

                    var date = Calendar.getInstance().time
                    var medication = Medication(0,"med1",1,"descr")
                    medicationDao.addMedication(medication)

                    var reminder = Reminder(0,date, medication,1,date)
                    reminderDao.addReminder(reminder)
                }
            }
        }
    }

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }
}