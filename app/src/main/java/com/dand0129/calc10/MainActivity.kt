package com.dand0129.calc10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}


@Preview
@Composable
fun Home() {
    val displayShow = remember { mutableStateOf("") }
    val number1 = remember { mutableStateOf("") }
    val number2 = remember { mutableStateOf("") }
    val resultOfOperations = remember { mutableStateOf(0.0) }
    val typeOfOperation = remember { mutableStateOf("") }
    val onOff = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(66, 66, 66))
    ) {
        // Calculator design
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .height(430.dp)
                .width(270.dp)
                .background(
                    color = Color(190, 190, 190),
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 30.dp,
                        bottomEnd = 80.dp
                    )
                )
        ) {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    fontFamily = FontFamily(Font(R.font.sumahuevosfont)),
                    fontSize = 20.sp,
                    color = Color(51,51,153),
                    text = "SUMA HUEVOS"
                )
            }
            Box(modifier = Modifier.align(Alignment.Center)) {
                Row {
                    Box {
                        // Keyboard and Display
                        Column {
                            Spacer(modifier = Modifier.height(30.dp))
                            Display(
                                textToDisplay = displayShow.value,
                                powerOnOff = onOff.value
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Box {
                                // Numeric Keyboard
                                Column {
                                    Row {
                                        NumberButtons(
                                            onButtonClicked = { value ->
                                                if (!onOff.value) {
                                                    displayShow.value = ""
                                                } else {
                                                    displayShow.value += value
                                                }

                                            }
                                        )
                                        Column {
                                            OperativeButtons(
                                                operation = "+",
                                                onButtonClicked = { value ->
                                                    number1.value = displayShow.value
                                                    displayShow.value = ""
                                                    typeOfOperation.value = value
                                                }
                                            )
                                            OperativeButtons(
                                                operation = "-",
                                                onButtonClicked = { value ->
                                                    number1.value = displayShow.value
                                                    displayShow.value = ""
                                                    typeOfOperation.value = value
                                                }
                                            )
                                            OperativeButtons(
                                                operation = "x",
                                                onButtonClicked = { value ->
                                                    number1.value = displayShow.value
                                                    displayShow.value = ""
                                                    typeOfOperation.value = value
                                                }
                                            )
                                            /*
                                             */
                                        }
                                    }

                                    // Number 0 and result button
                                    Row {
                                        Box(modifier = Modifier.width(50.dp)) {
                                            Button(
                                                modifier = Modifier.width(40.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color(105, 105, 105)
                                                ),
                                                onClick = {
                                                    if (!onOff.value) {
                                                        displayShow.value = ""
                                                    } else {
                                                        displayShow.value += "0"
                                                    }
                                                }
                                            ) {
                                                Text(text = "0")
                                            }
                                        }
                                        Box(modifier = Modifier.width(50.dp)) {
                                            Button(
                                                modifier = Modifier.width(40.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color(105, 105, 105)
                                                ),
                                                onClick = {
                                                    if (!onOff.value) {
                                                        displayShow.value = ""
                                                    } else {
                                                        displayShow.value += "."
                                                    }
                                                }) {
                                                Text(text = ".")
                                            }
                                        }
                                        // Result button
                                        Box(modifier = Modifier.width(49.dp)) {
                                            ResultButton(
                                                onButtonClicked = {
                                                    number2.value = displayShow.value
                                                    if (!onOff.value){
                                                        displayShow.value = ""
                                                    } else if (number2.value == "") {
                                                    displayShow.value = "Enter Second Number"
                                                } else if (typeOfOperation.value == "+") {
                                                    resultOfOperations.value =
                                                        number1.value.toDouble() + number2.value.toDouble()
                                                    displayShow.value =
                                                        resultOfOperations.value.toString()
                                                    } else if (typeOfOperation.value == "-") {
                                                        resultOfOperations.value =
                                                            number1.value.toDouble() - number2.value.toDouble()
                                                        displayShow.value =
                                                            resultOfOperations.value.toString()
                                                    } else if (typeOfOperation.value == "x") {
                                                        resultOfOperations.value =
                                                            number1.value.toDouble() * number2.value.toDouble()
                                                        displayShow.value =
                                                            resultOfOperations.value.toString()
                                                    } else if (typeOfOperation.value == "/") {
                                                        resultOfOperations.value =
                                                            number1.value.toDouble() / number2.value.toDouble()
                                                        displayShow.value =
                                                            resultOfOperations.value.toString()
                                                    } else {
                                                        return@ResultButton
                                                    }
                                                }
                                            )
                                        }
                                        // End of result button
                                        OperativeButtons(
                                            operation = "/",
                                            onButtonClicked = { value ->
                                                number1.value = displayShow.value
                                                displayShow.value = ""
                                                typeOfOperation.value = value
                                            }
                                        )
                                    }
                                    // End of number 0 and result button

                                    // Delete button
                                    Row {
                                        Box(modifier = Modifier.width(50.dp)) {
                                            Button(
                                                modifier = Modifier.width(40.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color(105, 105, 105)
                                                ),
                                                onClick = {
                                                    displayShow.value = displayShow.value.dropLast(1)
                                                }) {
                                                Text(
                                                    text = "\u232b",
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                        Box(modifier = Modifier.width(50.dp)) {
                                        OnOffButton(onButtonClicked = {value->
                                            onOff.value = value
                                        })
                                        }
                                    }
                                    // End of delete button
                                }
                                // End of numeric keyboard
                            }
                        }
                        // End of keyboard and display
                    }
                }
            }
        }
        // End of calculator design
        /*
        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            val imageResource: Painter = painterResource(id = R.drawable.totoro)
            Image(
                painter = imageResource,
                contentDescription = "My Image"
            )
        }
        */
    }

}

@Composable
fun NumberButtons(
    onButtonClicked: (String) -> Unit
) {
    val numbers = remember { (1..9).toList() }
    val number = remember { mutableStateOf("") }

    Box(modifier = Modifier.width(150.dp)) {
        Column {
            for (i in 0..2) {
                Row {
                    for (j in 0..2) {
                        Box(modifier = Modifier.weight(0.20f)) {
                            Button(
                                modifier = Modifier.width(40.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(105, 105, 105)
                                ),
                                onClick = {
                                    val num = numbers[i * 3 + j]
                                    number.value = num.toString()
                                    onButtonClicked(number.value)
                                }) {
                                Text(text = "${numbers[i * 3 + j]}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OperativeButtons(
    operation: String = "+",
    onButtonClicked: (String) -> Unit
) {

    val op = remember { mutableStateOf(operation) }
    Button(
        modifier = Modifier.width(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(105, 105, 105)
        ),
        onClick = {
            onButtonClicked(op.value)
        }) {
        Text(
            text = op.value
        )
    }
}

@Composable
fun ResultButton(
    onButtonClicked: (String) -> Unit
) {
    Button(
        modifier = Modifier.width(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(105, 105, 105)
        ),
        onClick = {
            onButtonClicked("")
        }
    ) {
        Text(
            text = "="
        )
    }
}

@Composable
fun OnOffButton(
    onButtonClicked: (Boolean) -> Unit
) {
    val onOff = remember { mutableStateOf(false) }
    Button(
        modifier = Modifier.width(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(105, 105, 105)
        ),
        onClick = {
        onOff.value = !onOff.value
        onButtonClicked(onOff.value)
        }) {
        Text(text = "ψ")
    }
}

@Composable
fun Display(
    textToDisplay: String = "title",
    powerOnOff: Boolean = false
) {
    val textToShow = remember { mutableStateOf("") }
    val red = remember { mutableStateOf(0) }
    val green = remember { mutableStateOf(0) }
    val blue = remember { mutableStateOf(0) }
    if(!powerOnOff) {
        textToShow.value = ""
        red.value = 43
        green.value = 73
        blue.value = 43
    } else {
        textToShow.value = textToDisplay
        red.value = 155
        green.value = 188
        blue.value = 15
    }
    Box(
        modifier = Modifier
            .width(190.dp)
            .height(70.dp)
            .background(
                color = Color(red.value, green.value, blue.value),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            text = textToShow.value,
            textAlign = TextAlign.End,
            color = Color(48, 98, 48),
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.gameboyfont))
        )
    }
}
