package com.example.eyedroptracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medication(@PrimaryKey val mid: Int,
                      @ColumnInfo(name = "name") val Name: String?,
                      @ColumnInfo(name = "dosage") val Dosage: Int?,
                      @ColumnInfo(name = "desc") val Description: String?
)