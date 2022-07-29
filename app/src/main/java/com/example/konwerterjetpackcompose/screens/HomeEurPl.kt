package com.example.konwerterjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

var euroValue : Double = 5.0
var currentDate = "dupa"

@Composable
fun HomeEurPl() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground(listOf(Color.Yellow, Color.White), angle = 45f),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = String.format("%.2f zł", euroValue),
                fontSize = MaterialTheme.typography.h1.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(text = "Kurs euro z dnia $currentDate.",
                fontSize = MaterialTheme.typography.h5.fontSize,
            color = Color.Black)
        }
    }

}

@Composable
@Preview
fun HomeEurPlPreview() {
    HomeEurPl()
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