package com.example.compose01.data

data class TrainingInterval(
    val interval: Int,
    var type: IntervalType,

    ) {
    enum class IntervalType {
        SHORT, LONG
    }
}

