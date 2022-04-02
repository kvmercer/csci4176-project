package com.example.eyedroptracker.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "reminders")
data class Reminder(@PrimaryKey(autoGenerate = true) @NonNull val rid: Int,
                    @ColumnInfo(name = "date_range") val DateRange: Date?,
                    @Embedded val ReminderMed: Medication?,
                    @ColumnInfo(name = "interval") val Interval: Int?,
                    @ColumnInfo(name = "time") val Time: Date?
) : Serializable