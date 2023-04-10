package com.dand0129.calc10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dand0129.calc10.ui.theme.Calc10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val functions = Functions()
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
    val number2 = remember { mutableStateOf("")}
    val resultOfOperations = remember { mutableStateOf(0) }
    val typeOfOperation = remember { mutableStateOf("")}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)
    ) {
        // Calculator design
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .align(Alignment.CenterStart)
                .height(600.dp)
                .width(300.dp)
                .padding(bottom = 100.dp, start = 20.dp)
                .background(color = Color(194, 194, 194)
                )
        ) {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Suma Huevos")
            }
            Box(modifier = Modifier.align(Alignment.Center)) {
                Row {
                    Box {
                        // Keyboard and Display
                        Column {
                            Spacer(modifier = Modifier.height(30.dp))
                            Row{
                                Display(
                                textToDisplay = displayShow.value
                            )
                            }
                            Box() {
                                // Numeric Keyboard
                                Column {
                                    NumberButtons(
                                        onButtonClicked = { value ->
                                            displayShow.value += value
                                        }
                                    )
                                    // Number 0 and result button
                                    Row {
                                        Box(modifier = Modifier.width((66.7).dp)) {
                                            Button(
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color(105,105,105)
                                                ),
                                                onClick = {
                                                displayShow.value = ""
                                            }) {
                                                Text(text = "0")
                                            }
                                        }
                                        ResultButton(
                                            onButtonClicked = {
                                                number2.value = displayShow.value
                                                if (typeOfOperation.value == "+") {
                                                    resultOfOperations.value =
                                                        number1.value.toInt() + number2.value.toInt()
                                                    displayShow.value =
                                                        resultOfOperations.value.toString()
                                                } else if (typeOfOperation.value == "-") {
                                                    resultOfOperations.value =
                                                        number1.value.toInt() - number2.value.toInt()
                                                    displayShow.value =
                                                        resultOfOperations.value.toString()
                                                } else if (typeOfOperation.value == "x") {
                                                    resultOfOperations.value =
                                                        number1.value.toInt() * number2.value.toInt()
                                                    displayShow.value =
                                                        resultOfOperations.value.toString()
                                                } else if (typeOfOperation.value == "/") {
                                                    resultOfOperations.value =
                                                        number1.value.toInt() / number2.value.toInt()
                                                    displayShow.value =
                                                        resultOfOperations.value.toString()
                                                } else {
                                                    return@ResultButton
                                                }
                                            }
                                        )
                                    }
                                    // End of number 0 and result button
                                    // Delete button
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(105,105,105)
                                        ),
                                        onClick = {
                                        displayShow.value = displayShow.value.dropLast(1)
                                    }) {
                                        Text(
                                            text = "\u232b",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    // End of delete button
                                }
                                // End of numeric keyboard
                            }
                        }
                        // End of keyboard and display
                    }
                    Column{
                        Spacer(modifier = Modifier.height(80.dp))
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
                        OperativeButtons(
                            operation = "/",
                            onButtonClicked = { value ->
                                number1.value = displayShow.value
                                displayShow.value = ""
                                typeOfOperation.value = value
                            }
                        )
                    }
                }
            }
        }
        // End of calculator design
    }
}

@Composable
fun NumberButtons(
    onButtonClicked: (String) -> Unit
) {
    val numbers = remember { (1..9).toList() }
    val number = remember { mutableStateOf("") }

    Box(modifier = Modifier.width(200.dp)) {
        Column {
            for (i in 0..2) {
                Row {
                    for (j in 0..2) {
                        Box(modifier = Modifier.weight(0.33f)) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(105,105,105)
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
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(105,105,105)
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
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(105,105,105)
        ),
        modifier = Modifier.width(132.dp),
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
fun OnOffButton() {

}

@Composable
fun Display(
    textToDisplay: String = "title"
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .background(color = Color(88, 150, 88))
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = textToDisplay,
            textAlign = TextAlign.Center,
            color = Color(30,57,30)
        )
    }
}
