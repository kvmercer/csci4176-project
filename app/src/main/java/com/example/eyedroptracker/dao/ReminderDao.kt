package com.example.eyedroptracker.dao

import androidx.room.*
import com.example.eyedroptracker.models.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    suspend fun getAllReminders(): List<Reminder>

    @Query("SELECT * FROM reminder ORDER BY time ASC")
    fun getRemindersDefault(): Flow<List<Reminder>>

    //TODO: update queries needed

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReminder(reminder: Reminder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg reminders: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)
}