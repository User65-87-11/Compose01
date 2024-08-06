package com.example.compose01.data

data class TrainingData(
    val id: Int = IDgen.generate(),
    val title: String = "new",
    val repTime: Int = 10,
    val repNum: Int = 2,
    val repRest: Int = 3,
    val setNum: Int = 2,
    val setRest: Int = 5
) {


    fun totalTime(): Int {
        return ((repTime + repRest) * repNum) * setNum + setNum * setRest
    }


}

//singleton
object IDgen {
    private var count_: Int = 0
    val count: Int get() = count_


    fun generate(): Int {
        count_++
        return count_
    }

}