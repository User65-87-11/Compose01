package com.example.compose01.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TrainingDao {
    @Insert
    fun insert(trainingEntity:TrainingEntity)

    @Delete
    fun delete(trainingEntity:TrainingEntity)

    @Query("SELECT * FROM TrainingEntity")
    fun selectAll():List<TrainingEntity>
}