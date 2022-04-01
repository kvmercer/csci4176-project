package com.example.eyedroptracker.repositories

import androidx.annotation.WorkerThread
import com.example.eyedroptracker.dao.ReminderDao
import com.example.eyedroptracker.models.Reminder
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ReminderRepository(private val reminderDao: ReminderDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allReminders: Flow<List<Reminder>> = reminderDao.getRemindersDefault()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addReminder(reminder: Reminder) {
        reminderDao.addReminder(reminder)
    }
}