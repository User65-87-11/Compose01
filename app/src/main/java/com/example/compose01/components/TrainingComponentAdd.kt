package com.example.compose01.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun TrainingComponentAdd(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onAdd: (TrainingData) -> Unit,

    ) {
    var textSearch by remember {
        mutableStateOf("")
    }

    var alertColor by remember {
        mutableStateOf(Color.Blue)
    }
    val animColor by animateColorAsState(targetValue = if(alertColor == Color.Red) Color.Red else MaterialTheme.colorScheme.primary,
        label = ""

    )
    {
        alertColor = Color.Blue
    }


    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
            ) {

               TextField(
                    modifier = Modifier
                        .weight(2.5f)
                        .padding(2.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedLabelColor =  animColor
                    ),

                    value = textSearch,

                    onValueChange = {
                        textSearch = it
                        onSearch(it)
                    },
                    label = {
                        Text("Find/Add(4)")
                    },
                )
                Spacer(
                    modifier = Modifier.weight(0.25f)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    contentPadding = PaddingValues(2.dp),
                    onClick = {
                        textSearch = ""
                        onSearch("")
                    },
                ) {
                    Text("clear")
                }
                Spacer(
                    modifier = Modifier.weight(0.25f)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),

                    onClick = {
                        if (textSearch.length > 3) {
                            onAdd(TrainingData(title = textSearch))
                            textSearch = ""
                        } else {
                            alertColor = Color.Red
                        }
                    },
                ) {
                    Text("Add")
                }
            }
        }
    }
}