package com.example.compose01.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.compose01.data.DataRepository
import com.example.compose01.data.TrainingData
import com.example.compose01.data.TrainingInterval
import com.example.compose01.data.TrainingLocalRepository


class TrainingViewModel2 : ViewModel() {

    private var repo: DataRepository = DataRepository(TrainingLocalRepository())


    var items2 = mutableStateListOf<TrainingData>()

    var edited = mutableStateOf<TrainingData>(TrainingData())

    var listOfIntervals = mutableListOf<TrainingInterval>()

    init {
        setLiveData()
    }

    fun intervalList(edited: TrainingData): List<TrainingInterval> {
        val l = listOfIntervals
        // start er
        l.add(TrainingInterval(5, TrainingInterval.IntervalType.SHORT))

        for (i in 1..edited.setNum) {
            for (a in 1..edited.repNum) {
                l.add(TrainingInterval(edited.repTime, TrainingInterval.IntervalType.LONG))
                if (a == edited.repNum) {
                    l.add(TrainingInterval(edited.setRest, TrainingInterval.IntervalType.SHORT))

                } else {
                    l.add(TrainingInterval(edited.repRest, TrainingInterval.IntervalType.SHORT))
                }
            }
        }
        l.removeAt(l.size - 1)
        return l
    }

    fun onSave(oldTraining: TrainingData, newTraining: TrainingData) {


        repo.update(oldTraining, newTraining)
        setLiveData()


        //   setLiveData()
    }


    fun deleteTraining(training: TrainingData) {

        repo.delete(training)
        //  setLiveData()

        setLiveData()

    }

    fun addTraining(training: TrainingData) {
        repo.add(training)
        //   setLiveData()

        setLiveData()


    }

    private fun setLiveData(find: String = "") {
        items2.clear()
        repo.trainings.forEach { el ->
            if (find.isEmpty()) {
                items2.add(el)
            } else {
                if (el.title.lowercase().contains(find)) {
                    items2.add(el)
                }
            }
        }

    }

    fun onEdit(item: TrainingData) {
        edited.value = item
    }

    fun onSearch(search: String) {
        setLiveData(search)
    }
}