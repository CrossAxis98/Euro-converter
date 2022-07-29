package com.example.konwerterjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konwerterjetpackcompose.retrofit.Rates
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

var allRates: Rates? = null
val mapCurrencies = mutableStateOf<Map<String, Double?>>(mapOf())

@Composable
fun AllCurrencies() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground(listOf(Color.Yellow, Color.White), angle = 45f)
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.Center
    ) {
    LazyColumn {
        items(1) {
            mapCurrencies.value.forEach { (currency, value) ->
                PostRow(cur = currency, value = value)
            }
        }
    }

    }

}

@Composable
@Preview
fun AllCurrenciesPreview() {
    AllCurrencies()
}

@Composable
fun PostRow(cur: String, value: Double?) {
    Row {
        Text(
            String.format("$cur - %.2f", value),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
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