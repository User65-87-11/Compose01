package com.example.compose01.components

import androidx.lifecycle.MutableLiveData
import com.example.compose01.data.TrainingData

data class TrainingUIState(
    val data: TrainingData,
    var isEdited: Boolean = false,
) {

}