package com.example.eyedroptracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eyedroptracker.models.Medication

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication")
    fun getAllMedications(): List<Medication>

    @Insert
    fun insertAll(vararg medications: Medication)

    @Delete
    fun delete(medication: Medication)
}