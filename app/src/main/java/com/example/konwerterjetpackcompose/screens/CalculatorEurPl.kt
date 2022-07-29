package com.example.konwerterjetpackcompose.screens

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

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
            .gradientBackground(listOf(Color.Yellow, Color.White), angle = 45f)
            .padding(10.dp)
    ) {
        Column( modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
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
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "-")
                        }
                        Box(contentAlignment = Alignment.Center)
                        {
                            Text(
                                String.format("%.2f zł", euroValueToCalculate),
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp
                            )
                        }
                        Button(
                            onClick = {
                                euroValueToCalculate+=0.01
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "+")
                        }
                    }

                }
            }

            OutlinedTextField(
                value = amountToExchange,
                onValueChange = { amountToExchange = it },
                label = { Text("Ile euro wymieniamy")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = {
                expectedAmout = euroValueToCalculate * amountToExchange.toDouble()
            }) {
                Text(
                    text = "Oblicz",
                    color = Color.Black,
                    fontSize = 20.sp
                )
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

private fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {

        val angleRad = angle / 180f * Math.PI
        val x = kotlin.math.cos(angleRad).toFloat()
        val y = kotlin.math.sin(angleRad).toFloat()
        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)
        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)