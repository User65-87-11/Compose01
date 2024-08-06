package com.example.compose01.components

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose01.data.DataRepository
import com.example.compose01.data.TrainingData
import com.example.compose01.data.TrainingLocalRepository
import java.util.Locale


class TrainingViewModel : ViewModel() {/*
    * Acts as variable storage that persists longer that a view
    *
    * 1. update UI
    * 2. should be selected training 
    * */


    private var trainingLiveData = MutableLiveData<List<TrainingUIState>>()

    val trainings: LiveData<List<TrainingUIState>> get() = trainingLiveData

    private  var repo: DataRepository = DataRepository(TrainingLocalRepository())

  //  val aaa: List<TrainingData> get() = repo.trainings

    //val repoData: List<TrainingData> get() = repo.trainings

    //  var exitedTraining = MutableLiveData<TrainingData>()

    init {
     //   setLiveData()


        // trainingLiveData =
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

    fun saveTraining(
        idx: Int,
        title: String,
        repNum: Int,
        repTime: Int,
        repRest: Int,
        setNum: Int,
        setRest: Int,
    ) {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.

      //  setLiveData()
    }

    fun saveTraining(oldTraining: TrainingData, newTraining: TrainingData) {
        repo.update(oldTraining, newTraining)
     //   setLiveData()
    }

    fun deleteTraining(training: TrainingData) {

        repo.delete(training)
      //  setLiveData()
    }

    fun addTraining(training: TrainingData) {
        repo.add(training)
     //   setLiveData()
    }

    fun onSearch(search: String) {
      //  setLiveData(search)
    }

    fun addTraining(
        title: String,
        repNum: Int,
        repTime: Int,
        repRest: Int,
        setNum: Int,
        setRest: Int,
    ) {

      //  setLiveData()


    }
}