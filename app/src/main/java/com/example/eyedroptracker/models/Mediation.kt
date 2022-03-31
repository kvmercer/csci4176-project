package com.example.eyedroptracker.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Medication(@PrimaryKey(autoGenerate = true) @NonNull val mid: Int,
                      @ColumnInfo(name = "name") val Name: String?,
                      @ColumnInfo(name = "dosage") val Dosage: Int?,
                      @ColumnInfo(name = "desc") val Description: String?
) : Serializable