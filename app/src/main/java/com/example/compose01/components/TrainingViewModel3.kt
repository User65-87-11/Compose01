package com.example.compose01.components

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose01.data.DataRepository
import com.example.compose01.data.TrainingData
import com.example.compose01.data.TrainingLocalRepository
import java.util.Locale


class TrainingViewModel3 : ViewModel() {

    private var trainingLiveData = MutableLiveData<List<TrainingUIState>>()

    val trainings: LiveData<List<TrainingUIState>> get() = trainingLiveData

    private  var repo: DataRepository = DataRepository(TrainingLocalRepository())

    init {
         setLiveData()

    }

    private fun setLiveData(find: String="") {
        trainingLiveData.value = repo.trainings.mapNotNull { item ->

            if (find.isNotEmpty()) {
                if (item.title.lowercase(Locale.ENGLISH).contains(find)) {
                    TrainingUIState(data = item)
                } else {
                    null
                }
            } else {
                TrainingUIState(data = item)
            }
        }
    }

    fun saveTraining(oldTraining: TrainingData, newTraining: TrainingData) {


        repo.update(oldTraining, newTraining)
        setLiveData()
    }

    fun deleteTraining(training: TrainingData) {

        repo.delete(training)
        setLiveData()


    }

    fun addTraining(training: TrainingData) {
        repo.add(training)
        setLiveData()


    }

    fun onSearch(search: String) {
        setLiveData(search)
    }
}