package com.example.konwerterjetpackcompose.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {

    //@GET("/exchangerates_data/latest?symbols=PLN&base=EUR&apikey=OwZ29ZNnhQMwtLmkAsJiQNzqI9lYsQRr")
    @GET("/exchangerates_data/latest?base=EUR&apikey=OwZ29ZNnhQMwtLmkAsJiQNzqI9lYsQRr")
    fun getExchangeRates() : Call<ExchangeRates>
}