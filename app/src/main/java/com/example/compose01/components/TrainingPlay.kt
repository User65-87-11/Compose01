package com.example.compose01.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
    var repTime by remember { mutableStateOf(data.repTime.toString()) }
    var repNum by remember { mutableStateOf(data.repNum.toString()) }
    var repRest by remember { mutableStateOf(data.repRest.toString()) }
    var setNum by remember { mutableStateOf(data.setNum.toString()) }
    var setRest by remember { mutableStateOf(data.setRest.toString()) }

    var openAlertDialog by remember { mutableStateOf(false) }


    var smallProgress by remember {
        mutableFloatStateOf(0f)
    }
    var largeProgress by remember {
        mutableFloatStateOf(0f)
    }

    var totalTime by remember {
        mutableIntStateOf(5)
    }
    var counter by remember {
        mutableIntStateOf(5)
    }
    var counterSec by remember {
        mutableIntStateOf(0)
    }
    var isStartedLarge by remember {
        mutableStateOf(false)
    }
    var isStartedSmall by remember {
        mutableStateOf(false)
    }
    var currentLoop by remember {
        mutableIntStateOf(0)
    }
    var currentSettings by remember {
        mutableStateOf(vm.listOfIntervals[currentLoop])
    }

    /*
    * isStarted
    * pick current settings
    * increment current loop after launched effect is complete
    * */
    var isStarted by remember {
        mutableStateOf(false)
    }
//    var inter by remember {
//        mutableStateOf( data.intervalList())
//    }
    LaunchedEffect(isStarted, currentLoop) {
        if (isStarted) {
            if (currentLoop < vm.listOfIntervals.size) {


                currentSettings = vm.listOfIntervals[currentLoop]

                counterSec = currentSettings.interval
                counter = counterSec * 1000

                totalTime = counter
                when (currentSettings.type) {
                    TrainingInterval.IntervalType.SHORT -> {
                        isStartedSmall = true
                    }

                    TrainingInterval.IntervalType.LONG -> {
                        isStartedLarge = true
                    }

                }
            }else
            {
                isStarted = false;
            }
        }
    }
    LaunchedEffect(isStartedLarge, counter) {

        if (isStartedLarge) {
            if (counter > 0) {
                delay(100)
                counter -= 100
                if(counter % 1000 == 0)
                counterSec -= 1
                largeProgress = counter .toFloat() / totalTime .toFloat()
            } else {

                isStartedLarge = false
                currentLoop += 1
            }
        }
    }
    LaunchedEffect(isStartedSmall, counter) {

        if (isStartedSmall) {
            if (counter > 0) {
                delay(100)
                counter -= 100
                if(counter % 1000 == 0)
                counterSec -=1
                smallProgress = counter .toFloat() / totalTime .toFloat()
            } else {

                isStartedSmall = false
                currentLoop += 1
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
                    trackColor = Color.Red,
                    strokeCap = StrokeCap.Round,
                    progress = { largeProgress },
                    color = Color.Yellow,
                )
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 10.dp,
                    trackColor = Color.Green,
                    strokeCap = StrokeCap.Round,
                    progress = { smallProgress },
                    color = Color.Red,
                )
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "00:${String.format("%02d", counterSec)}", fontSize = 50.sp)
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