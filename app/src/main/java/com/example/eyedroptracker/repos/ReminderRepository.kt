package com.example.eyedroptracker.repos

import androidx.annotation.WorkerThread
import com.example.eyedroptracker.dao.ReminderDao
import com.example.eyedroptracker.models.Reminder
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val reminderDao: ReminderDao) {

    val allReminders: Flow<List<Reminder>> = reminderDao.getRemindersDefault()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addReminder(reminder: Reminder) {
        reminderDao.addReminder(reminder)
    }
}