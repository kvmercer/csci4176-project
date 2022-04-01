package com.example.eyedroptracker.repos

import androidx.annotation.WorkerThread
import com.example.eyedroptracker.dao.MedicationDao
import com.example.eyedroptracker.models.Medication
import kotlinx.coroutines.flow.Flow

class MedicationRepository(private val medicationDao: MedicationDao) {

    val allMedications: Flow<List<Medication>> = medicationDao.getAllMedications()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addMedication(medication: Medication) {
        medicationDao.addMedication(medication)
    }
}