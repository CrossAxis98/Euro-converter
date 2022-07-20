package com.example.konwerterjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.konwerterjetpackcompose.retrofit.Rates
import com.example.konwerterjetpackcompose.retrofit.RetrofitInstance.retrofit
import com.example.konwerterjetpackcompose.screens.currentDate
import com.example.konwerterjetpackcompose.screens.euroValue
import com.example.konwerterjetpackcompose.ui.theme.KonwerterJetpackComposeTheme
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

const val TAG = "Main Activity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isVisible = true
            var isVisibleObs by remember { mutableStateOf(isVisible)}
            KonwerterJetpackComposeTheme {
                lifecycleScope.launchWhenCreated {
                    val response = try {
                        retrofit.getExchangeRates().awaitResponse()
                    } catch (e: IOException) {
                        Log.e(TAG, "IOException, you might not have internet connection")
                        isVisible = false
                        return@launchWhenCreated
                    } catch (e: HttpException) {
                        Log.e(TAG, "HttpException, unexpected response")
                        isVisible = false
                        return@launchWhenCreated
                    }

                    Log.d(TAG, "XXX ${response.isSuccessful}")
                    isVisible = false
                    if (response.isSuccessful && response.body() != null) {
                        Log.d(TAG, "XXX ${response.body()!!}")
                        euroValue = response.body()!!.rates.PLN
                        currentDate = response.body()!!.date
                    } else {
                        Log.e(TAG, "Response not successful")
                    }
                }
                if (isVisibleObs) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator(
                            backgroundColor = Color.White,
                            progress = 0.5f,
                            color = Color.Blue
                        )
                    }
                } else {
                    Log.d(TAG, "XXX dupa")
                    MainScreen()
                }
            }
        }

    }



}
