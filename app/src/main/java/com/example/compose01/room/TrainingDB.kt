package com.example.compose01.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TrainingEntity::class], version = 1, exportSchema = false)
abstract class TrainingDB:RoomDatabase() {


    abstract fun trainingDao():TrainingDao

    companion object{

    }
}