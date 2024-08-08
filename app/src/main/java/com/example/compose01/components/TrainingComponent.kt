package com.example.compose01.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
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
fun TrainingComponent(
    data: TrainingData,
    modifier: Modifier = Modifier,
    onEdit:() ->Unit,
    onDelete: () -> Unit,
    onStart: () -> Unit,

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
                .padding(start = 10.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = data.title,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    modifier = Modifier
                        .weight(5f)
                        ,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEdit()
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
                        onStart()
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



             //   Text("Title: $title")
                Text("Repetition time: ${data.repTime} sec")
                Text("Repetition rest: ${data.repRest} sec")
                Text("Repetition number: ${data.repNum} times")
                Text("Set rest: ${data.setRest} sec")
                Text("Set number: ${data.setNum} times")
                Text("Total time: ${String.format("%02d:%02d",data.totalTime()/60,data.totalTime()%60)}")


                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth())
                {
                    IconButton(
                        modifier = Modifier
                            .padding(20.dp)
                            .size(40.dp)
                            .alpha(0.5f)

                          ,

                        onClick = {
                            onDelete()
                            expanded = false

                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                   // Spacer(modifier = Modifier.width(50.dp))
                }


            }

        }
    }
}