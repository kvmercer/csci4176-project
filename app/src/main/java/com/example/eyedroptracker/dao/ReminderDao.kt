package com.example.eyedroptracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eyedroptracker.models.Reminder

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    fun getAllReminders(): List<Reminder>

    @Insert
    fun insertAll(vararg reminders: Reminder)

    @Delete
    fun delete(reminder: Reminder)
}