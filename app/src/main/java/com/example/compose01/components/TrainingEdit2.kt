package com.example.compose01.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.compose01.data.TrainingData


@Composable
fun TrainingEdit2(
    data: TrainingData,
    modifier: Modifier = Modifier,
    onSave: (TrainingData, TrainingData) -> Unit,
    navigateHome:()->Unit
) {

    var title by remember {
        mutableStateOf(data.title)
    }
    var repTime by remember { mutableStateOf(data.repTime.toString()) }
    var repNum by remember { mutableStateOf(data.repNum.toString()) }
    var repRest by remember { mutableStateOf(data.repRest.toString()) }
    var setNum by remember { mutableStateOf(data.setNum.toString()) }
    var setRest by remember { mutableStateOf(data.setRest.toString()) }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
           ,

        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth())
                {
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            if (it.length < 60) {
                                title = it
                            }
                        },
                        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(text = "Title")
                        },
                        maxLines = 2,
                        modifier = Modifier.weight(1f),


                        )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    OutlinedTextField(
                        value = repTime,
                        onValueChange = {
                            if (it.length in 0..2) {
                                repTime = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Rep time")
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedTextField(
                        value = repRest,
                        onValueChange = {
                            if (it.length in 0..2) {
                                repRest = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Rep rest")
                        },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedTextField(
                        value = repNum,
                        onValueChange = {
                            if (it.length in 0..2) {
                                repNum = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Rep num")
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)


                ) {
                    OutlinedTextField(
                        value = setNum,
                        onValueChange = {
                            if (it.length in 0..2) {
                                setNum = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Set num")
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedTextField(
                        value = setRest,
                        onValueChange = {
                            if (it.length in 0..2) {
                                setRest = it
                            }

                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Set rest")
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.weight(1f))


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)


                ) {
                    Button(modifier = Modifier
                        .weight(1f)
                        .align(
                            alignment = Alignment.CenterVertically
                        ),

                        onClick = {
                            val newData = data.copy(
                                title = title,
                                repNum = repNum.toInt(),
                                repTime = repTime.toInt(),
                                repRest = repRest.toInt(),
                                setNum = setNum.toInt(),
                                setRest = setRest.toInt(),
                            )

                            onSave(data, newData);
                            navigateHome()

                        }) {
                        Text(text = "save")

                    }



                }

            }

    }
}