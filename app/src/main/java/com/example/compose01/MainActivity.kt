package com.example.compose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.compose01.components.TrainingApp
import com.example.compose01.components.TrainingViewModel2
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
        val viewModel by viewModels<TrainingViewModel2>()

        //   viewModel.trainings.observe(this) { data ->


        enableEdgeToEdge()
        setContent {


            Compose01Theme {

                    TrainingApp(viewModel = viewModel)

            }
        }
    }
}





