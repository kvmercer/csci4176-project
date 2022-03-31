package com.example.eyedroptracker.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(@PrimaryKey @NonNull @ColumnInfo(name="f_name") val firstName: String,
                @ColumnInfo(name = "l_name") val lastName: String?
) : Serializable