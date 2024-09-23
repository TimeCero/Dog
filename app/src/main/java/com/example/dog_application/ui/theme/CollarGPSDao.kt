package com.example.dog_application.ui.theme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CollarGPSDao {
    @Query("SELECT * FROM CollarGPS")
    suspend fun getAll(): List<CollarGPS>

    @Insert
    suspend fun insert(collarGPS: CollarGPS)

    @Query("DELETE FROM CollarGPS WHERE id = :id")
    suspend fun deleteById(id: Int)
}
