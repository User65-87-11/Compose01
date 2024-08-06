package com.example.compose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.compose01.components.TrainingComponent
import com.example.compose01.components.TrainingComponentAdd
import com.example.compose01.components.TrainingViewModel3
import com.example.compose01.ui.theme.Compose01Theme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val viewModel by viewModels<TrainingViewModel>()
//        enableEdgeToEdge()
//        setContent {
//            Compose01Theme {
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//
//                ) { innerPadding ->
//
//                    TrainingComponentList(viewModel = viewModel,modifier = Modifier.padding(innerPadding))
//
//                }
//            }
//        }
     //   val viewModel by viewModels<TrainingViewModel3>()

        //   viewModel.trainings.observe(this) { data ->

//
//        viewModel.trainings.observe(this) { data ->
//            enableEdgeToEdge()
//            setContent {
//
//
//                Compose01Theme {
//                    Scaffold(
//                        modifier = Modifier.fillMaxSize(),
//
//                        ) { innerPadding ->
//
//                        LazyColumn(
//                            modifier = Modifier.padding(innerPadding),
//
//
//                            ) {
//
//                            item {
//                                TrainingComponentAdd(
//                                    modifier = Modifier,
//                                    onAdd = viewModel::addTraining,
//                                    onSearch = viewModel::onSearch
//                                )
//                            }
//                            data.forEach { dd ->
//                                item {
//                                    TrainingComponent(
//                                        data = dd.data,
//                                        modifier = Modifier,
//
//
//
//                                    )
//                                }
//                            }
//
////                                items {
////                                    TrainingComponent(
////                                        data = rd,
////                                        modifier = Modifier,
////                                        onStart = { },
////                                        onSave = viewModel::saveTraining,
////                                        onDelete = viewModel::deleteTraining,
////                                    )
////                                }
//                        }
//                    }
//
//                }
//
//            }
//        }
    }

}



