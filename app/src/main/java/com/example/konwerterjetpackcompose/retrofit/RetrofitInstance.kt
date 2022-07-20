package com.example.konwerterjetpackcompose.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit: CurrencyApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }
}