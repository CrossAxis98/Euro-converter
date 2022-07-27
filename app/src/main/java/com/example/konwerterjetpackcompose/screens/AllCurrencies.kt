package com.example.konwerterjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.konwerterjetpackcompose.retrofit.Rates

var allRates: Rates? = null
val mapCurrencies = mutableStateOf<Map<String, Double?>>(mapOf())

@Composable
fun AllCurrencies() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
//        Text(text = "Tutaj będą wypisane wszystkie waluty",
//            fontSize = MaterialTheme.typography.h3.fontSize,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            textAlign = TextAlign.Center )
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
        Text(String.format("$cur %.2f", value), fontSize = 15.sp)
    }
}