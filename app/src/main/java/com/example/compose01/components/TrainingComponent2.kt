package com.example.compose01.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.compose01.data.TrainingData


//@Composable
//fun TrainingComponentList(viewModel: TrainingViewModel,modifier: Modifier = Modifier){
//    var idx:Int = 0
//    LazyColumn(modifier = modifier) {
//        items(viewModel.trainings.value){ rd ->
//                TrainingComponent(idx = idx++,data = rd,viewModel,modifier =modifier)
//
//        }
//    }
//}

@Composable
fun TrainingComponent2(
    data: TrainingData,
    modifier: Modifier = Modifier,
    onSave: (TrainingData, TrainingData) -> Unit,
    onDelete: (TrainingData) -> Unit,
    onStart: (TrainingData) -> Unit,

    ) {

    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f, label = ""

    )

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)

            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing,
                )

            ),
        onClick = {
            expanded = !expanded

        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = data.title,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onStart(data)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            //  .fillMaxSize()
                            .alpha(0.5f),

                        )
                }


                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onStart(data)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start",
                        modifier = Modifier
                            //    .fillMaxSize()
                            .alpha(0.5f),

                        )
                }
                //▶️
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f)
                        .weight(1f)
                        .rotate(rotationState),

                    onClick = {
                        expanded = !expanded

                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop down arrow",
                    )


                }

            }
            if (expanded) {

                var title by remember {
                    mutableStateOf(data.title)
                }
                var repTime by remember { mutableStateOf(data.repTime.toString()) }
                var repNum by remember { mutableStateOf(data.repNum.toString()) }
                var repRest by remember { mutableStateOf(data.repRest.toString()) }
                var setNum by remember { mutableStateOf(data.setNum.toString()) }
                var setRest by remember { mutableStateOf(data.setRest.toString()) }

                Row(modifier = Modifier.fillMaxWidth()) {
//                    OutlinedTextField(
//                        value = title,
//                        onValueChange = {
//                            if (it.length < 60) {
//                                title = it
//                            }
//                        },
//                        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                        label = {
//                            Text(text = "Title")
//                        },
//                        maxLines = 2,
//                        modifier = Modifier.weight(1f),
//
//
//                        )
                    Text(text = "Title: $title")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
//                    OutlinedTextField(value = repTime,
//                        onValueChange = {
//                            if (it.length in 0..2) {
//                                repTime = it
//                            }
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = {
//                            Text(text = "Rep time")
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
                    Text("Repetition time: $repTime")
                    Spacer(modifier = Modifier.width(15.dp))
//                    OutlinedTextField(value = repRest,
//                        onValueChange = {
//                            if (it.length in 0..2) {
//                                repRest = it
//                            }
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = {
//                            Text(text = "Rep rest")
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
                    Text("Repetition rest: $repRest")
                    Spacer(modifier = Modifier.width(15.dp))
//                    OutlinedTextField(value = repNum,
//                        onValueChange = {
//                            if (it.length in 0..2) {
//                                repNum = it
//                            }
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = {
//                            Text(text = "Rep num")
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
                    Text("Repetition number: $repNum")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)


                ) {
//                    OutlinedTextField(value = setNum,
//                        onValueChange = {
//                            if (it.length in 0..2) {
//                                setNum = it
//                            }
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = {
//                            Text(text = "Set num")
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
                    Text("Set number: $setNum")
                    Spacer(modifier = Modifier.width(15.dp))
//                    OutlinedTextField(
//                        value = setRest,
//                        onValueChange = {
//                            if (it.length in 0..2) {
//                                setRest = it
//                            }
//
//                        },
//
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = {
//                            Text(text = "Set rest")
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
                    Text("Set rest: $setRest")

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)


                ) {
//                    Button(modifier = Modifier
//                        .weight(1f)
//                        .align(
//                            alignment = Alignment.CenterVertically
//                        ),
//
//                        onClick = {
//                            val newData = data.copy(
//                                title = title,
//                                repNum = repNum.toInt(),
//                                repTime = repTime.toInt(),
//                                repRest = repRest.toInt(),
//                                setNum = setNum.toInt(),
//                                setRest = setRest.toInt(),
//                            )
//
//                            onSave(data, newData)
//
//                        }) {
//                        Text(text = "save")
//
//                    }
                  //  Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier
                            .size(40.dp)
                            .alpha(0.5f)
                            .weight(1f),

                        onClick = {
                            onDelete(data)
                            expanded = false
//
//                            viewModel.deleteTraining(
//                                idx = idx,
//
//                            )

                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.fillMaxSize()
                        )


                    }

                }

            }

        }
    }
}