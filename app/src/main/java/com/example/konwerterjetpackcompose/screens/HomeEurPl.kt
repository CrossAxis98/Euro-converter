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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

//const val euroValue = "4,78"
//const val currentDate = "16.07.2022"

var euroValue : Double = 5.0
var currentDate = ""

@Composable
fun HomeEurPl() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "$euroValue zł",
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