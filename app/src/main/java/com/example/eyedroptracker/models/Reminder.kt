package com.example.eyedroptracker.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Reminder(@PrimaryKey val rid: Int,
                    @ColumnInfo(name = "date_range") val DateRange: Date?,
                    @Embedded @ColumnInfo(name = "r_med") val ReminderMed: Medication?,
                    @ColumnInfo(name = "interval") val Interval: Int?,
                    @ColumnInfo(name = "time") val Time: Date?
)