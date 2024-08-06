package com.example.compose01.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val title: String,
    val repTime: Int ,
    val repNum: Int ,
    val repRest: Int,
    val setNum: Int ,
    val setRest: Int ,
)