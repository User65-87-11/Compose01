package com.example.compose01.data

data class TrainingInterval(
    val interval: Int,
    var type: IntervalType,

    ) {
    enum class IntervalType {
        REPREST, REP,PREP,SETREST,FINISH;

        fun stringVal():String{
            return when(this){
                REPREST-> "Interval rest"
                REP-> "Training time"
                PREP-> "Start in..."
                SETREST-> "Set rest"
                FINISH -> "Finished"
            }
        }
    }
}

