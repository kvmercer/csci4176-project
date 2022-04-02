package com.example.eyedroptracker

import android.app.Application
import com.example.eyedroptracker.repos.MedicationRepository
import com.example.eyedroptracker.repos.ReminderRepository
import com.example.eyedroptracker.repos.UserRepository
import com.example.eyedroptracker.utils.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EyedropTrackerApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val reminderRepository by lazy { ReminderRepository(database.reminderDao()) }
    val medicationRepository by lazy { MedicationRepository(database.medicationDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
}