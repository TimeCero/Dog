package com.example.dog_application.ui.theme

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CollarGPS::class], version = 1)
abstract class CollarGPSDatabase : RoomDatabase() {
    abstract fun collarGPSDao(): CollarGPSDao
}
