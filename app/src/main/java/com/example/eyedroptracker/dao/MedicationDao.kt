package com.example.eyedroptracker.dao

import androidx.room.*
import com.example.eyedroptracker.models.Medication
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medications")
    fun getAllMedications(): Flow<List<Medication>>

    @Query("DELETE FROM medications")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedication(medication: Medication)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg medications: Medication)

    @Delete
    suspend fun delete(medication: Medication)
}