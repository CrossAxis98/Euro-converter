package com.example.konwerterjetpackcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.konwerterjetpackcompose.retrofit.Rates
import com.example.konwerterjetpackcompose.retrofit.RetrofitInstance.retrofit
import com.example.konwerterjetpackcompose.screens.currentDate
import com.example.konwerterjetpackcompose.screens.euroValue
import com.example.konwerterjetpackcompose.ui.theme.KonwerterJetpackComposeTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import kotlin.math.roundToInt

const val TAG = "Main Activity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLoading = mutableStateOf(false)

        lifecycleScope.launchWhenCreated {
            isLoading.value = true
            val response = try {
                retrofit.getExchangeRates().awaitResponse()
            } catch (e: IOException) {
                isLoading.value = false
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                isLoading.value = false
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            Log.d(TAG, "XXX ${response.isSuccessful}")
            if (response.isSuccessful && response.body() != null) {
                isLoading.value = false
                Log.d(TAG, "XXX ${response.body()!!}")
                euroValue = (response.body()!!.rates.PLN!! * 100).roundToInt() / 100.0
                currentDate = response.body()!!.date
            } else {
                isLoading.value = false
                Log.e(TAG, "Response not successful")
            }
        }

        setContent {
            KonwerterJetpackComposeTheme {
                    if (isLoading.value) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        MainScreen()
                    }
            }

        }
    }
}
