package com.example.konwerterjetpackcompose.screens

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun CalculatorEurPl() {
    var euroValueToCalculate by remember { mutableStateOf(euroValue)}
    var amountToExchange by remember { mutableStateOf("0")}
    var expectedAmout by remember {
        mutableStateOf(0.0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
        //contentAlignment = Alignment.Center
    ) {
        Column( modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                value = amountToExchange,
                onValueChange = { amountToExchange = it },
                label = { Text("Ile euro wymieniamy")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
//            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(
                        text = "Po jakim kursie da Krzysiu:",
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                euroValueToCalculate-=0.01
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.border(1.dp, Color.Black)
                        ) {
                            Text(text = "-")
                        }
                        Box(contentAlignment = Alignment.Center)
                        {
                            Text(
                                "$euroValueToCalculate zł",
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp
                            )
                        }
                        Button(
                            onClick = {
                                euroValueToCalculate+=0.01
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.border(
                                1.dp,
                                Color.Black
                            )
                        ) {
                            Text(text = "+")
                        }
                    }
                }
            }
            Button(onClick = {
                expectedAmout = euroValueToCalculate * amountToExchange.toDouble()//) * 100).roundToInt() / 100.0
            }) {
                Text(text = "Oblicz")
            }

            Text(
                text = String.format("%.2f zł", expectedAmout),
                fontSize = MaterialTheme.typography.h2.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
@Preview
fun CalculatorEurPlPreview() {
    CalculatorEurPl()
}