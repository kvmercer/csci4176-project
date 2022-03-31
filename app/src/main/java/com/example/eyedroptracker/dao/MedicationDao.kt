package com.example.eyedroptracker.dao

import androidx.room.*
import com.example.eyedroptracker.models.Medication

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication")
    suspend fun getAllMedications(): List<Medication>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedication(medication: Medication)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg medications: Medication)

    @Delete
    suspend fun delete(medication: Medication)
}