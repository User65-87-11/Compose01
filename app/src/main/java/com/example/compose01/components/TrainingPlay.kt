package com.example.compose01.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose01.data.TrainingData
import com.example.compose01.data.TrainingInterval
import kotlinx.coroutines.delay


@Composable
fun TrainingPlay(
    data: TrainingData,
    vm: TrainingViewModel2,
    modifier: Modifier = Modifier,
    onPlay: () -> Unit,
    navigateHome: () -> Unit
) {

    var title by remember {
        mutableStateOf(data.title)
    }

    var totalTurns by remember { mutableStateOf(data.repNum * data.setNum) }
    var totalSets by remember { mutableStateOf(data.setNum) }
    var trainingTotalTime by remember { mutableIntStateOf(data.totalTime()) }

    var openAlertDialog by remember { mutableStateOf(false) }

    var formattedTotalTime =
        String.format("%02d:%02d", data.totalTime() / 60, data.totalTime() % 60)

    var totalTime by remember {
        mutableIntStateOf(5)
    }
    var counter by remember {
        mutableStateOf(0, neverEqualPolicy())
    }


    var currentLoop by remember {
        mutableStateOf(0)
    }

    var isStarted by remember {
        mutableStateOf(false)
    }
    var loopType by remember {
        mutableStateOf(vm.listOfIntervals[0].type.stringVal())
    }
    var totalProgAnim by remember {
        mutableStateOf(Animatable(0f))


    }







    LaunchedEffect(isStarted, currentLoop) {
        if (isStarted) {
            if (currentLoop < vm.listOfIntervals.size) {

                val temp = vm.listOfIntervals[currentLoop]


                counter = temp.interval
                totalTime = counter
                Log.d("animateTo", "1f")


                loopType = temp.type.stringVal()

                if (temp.type != TrainingInterval.IntervalType.FINISH && temp.type != TrainingInterval.IntervalType.PREP) {




                    if (temp.type == TrainingInterval.IntervalType.REP) {
                        totalTurns--

                        if (  (1 + totalTurns ) % data.setNum ==0  || totalTurns ==0) {

                            if(totalSets>0)
                            {
                                totalSets--
                            }

                        }
                    }


                    totalProgAnim = Animatable(0f)
                    totalProgAnim.animateTo(
                        1f,
                        animationSpec = tween(
                            totalTime * 1000, easing = LinearEasing
                        ),
                    )

                }

            } else {
                isStarted = false
            }
        }
    }


    LaunchedEffect(isStarted, counter) {

        if (isStarted) {
            if (counter > 0) {
                delay(1000)
                counter -= 1

                if (loopType != TrainingInterval.IntervalType.PREP.stringVal()
                    &&
                    loopType != TrainingInterval.IntervalType.FINISH.stringVal()

                    ) {
                    trainingTotalTime--
                }

            } else {
                currentLoop += 1

                Log.d("currentLoop", "+1")
                Log.d("totalProgAnim", totalProgAnim.value.toString())


            }
        }
    }



    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),

        ) {


        Column(
            modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = title)
            Text(
                text = "Total time: ${
                    String.format(
                        "%02d:%02d",
                        trainingTotalTime / 60,
                        trainingTotalTime % 60
                    )
                } / ${formattedTotalTime}"
            )
            Text(text = "Total reps: ${totalTurns} / ${data.repNum * data.setNum}")
            Text(text = "Sets: ${totalSets} / ${data.setNum}")


            Box(
                modifier = Modifier
                    .padding(40.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,

                ) {
                Surface(
                    modifier = Modifier
                        .size(300.dp)
                        .alpha(0.2f),
                    color = Color.Gray,

                    shape = CircleShape,
                ) {}
                CircularProgressIndicator(

                    modifier = Modifier.size(250.dp),
                    strokeWidth = 20.dp,
                    trackColor = Color.DarkGray,
                    strokeCap = StrokeCap.Round,
                    progress = { totalProgAnim.value },
                    color = Color.Gray,

                    )

                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = loopType)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "00:${String.format("%02d", counter)}", fontSize = 50.sp)
                    }

                }


            }


            Button(onClick = {

                openAlertDialog = true
            }) {
                Text(text = "Start")
            }
            if (openAlertDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openAlertDialog = false

                    },
                    confirmButton = {
                        Button(onClick = {
                            if (isStarted) return@Button

                            onPlay()
                            isStarted = true
                            currentLoop = 0

                            openAlertDialog = false

//                            smallProgress = 1f
//                            counter = 5
//                            totalTime = 5
//                            isStartedSmall = true
//
//
//                            openAlertDialog = false


                        }) {
                            Text(text = "Yes")

                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            openAlertDialog = false

                        }) {
                            Text(text = "No")

                        }
                    },
                    title = { Text(text = "Sure?") },

                    )
            }
        }

    }
}