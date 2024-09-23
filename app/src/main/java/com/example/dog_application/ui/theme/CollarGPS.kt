package com.example.dog_application.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollarGPS(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombrePerro: String,
    val ubicacion: String
)
