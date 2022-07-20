package com.example.konwerterjetpackcompose.retrofit

data class ExchangeRates(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)