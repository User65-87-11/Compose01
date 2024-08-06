package com.example.compose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose01.components.TrainingApp
import com.example.compose01.components.TrainingComponent
import com.example.compose01.components.TrainingComponentAdd
import com.example.compose01.components.TrainingViewModel2
import com.example.compose01.ui.theme.Compose01Theme

class MainActivity2 : ComponentActivity() {


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





