package com.example.eyedroptracker.dao

import androidx.room.*
import com.example.eyedroptracker.models.Reminder

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    suspend fun getAllReminders(): List<Reminder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReminder(reminder: Reminder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg reminders: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)
}