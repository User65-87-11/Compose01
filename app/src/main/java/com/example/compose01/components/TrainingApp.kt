package com.example.compose01.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose01.R

@Composable
fun TrainingApp(
    viewModel: TrainingViewModel2,
) {

    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable(
                route = "home",

                ) {

                BackHandler {

                }

                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                ) {
                    item {
                        TrainingComponentAdd(
                            modifier = Modifier,
                            onAdd = viewModel::addTraining,
                            onSearch = viewModel::onSearch
                        )
                    }

                    items(viewModel.items2) { dd ->
                        TrainingComponent(data = dd, modifier = Modifier, onStart = {
                            navController.navigate(route = "play")
                            viewModel.onEdit(dd)
                            viewModel.intervalList(dd)
                        }, onDelete = { viewModel.deleteTraining(dd) }, onEdit = {
                            navController.navigate(route = "edit")
                            viewModel.onEdit(dd)
                        })
                    }
                    item {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(900.dp),
                            ) {
                            Image(
                                painterResource(id = R.drawable.rect5037),
                                contentDescription = "img",
                                modifier = Modifier
                                    .padding(100.dp)
                                    .alpha(0.2f),
                                )
                        }
                    }

                }

            }
            composable(
                route = "edit"
            ) {

                TrainingEdit(data = viewModel.edited.value,
                    onSave = viewModel::onSave,
                    modifier = Modifier.padding(innerPadding),
                    navigateHome = {
                        navController.navigate("home")
                    })


            }

            composable(
                route = "play"
            ) {

                TrainingPlay(data = viewModel.edited.value,
                    vm = viewModel,
                    onPlay = {},
                    modifier = Modifier.padding(innerPadding),
                    navigateHome = {
                        navController.navigate("home")
                    })


            }
        }
    }
}
