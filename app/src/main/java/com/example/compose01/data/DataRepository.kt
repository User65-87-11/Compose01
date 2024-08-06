package com.example.compose01.data

class DataRepository(
    private val localDataSource: TrainingLocalRepository,

    ) {

    val trainings:List<TrainingData> get() = localDataSource.trainingList



    fun add(
       item: TrainingData
    ) {
        localDataSource.trainingList.add(
            item
        )
    }


    fun delete(
        item: TrainingData
    ) {
        localDataSource.trainingList.remove(item)
    }

    fun update(
        oldVal:TrainingData,
        newVal:TrainingData
    ) {
        val idx =   localDataSource.trainingList.indexOf(oldVal);
        localDataSource.trainingList.add(idx,
            newVal
        )

        localDataSource.trainingList.remove(oldVal);
//        localDataSource.trainingList.add(newVal);

      //  localDataSource.trainingList[localDataSource.trainingList.indexOf(oldVal)] = newVal;
    }

}