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
import com.example.konwerterjetpackcompose.screens.*
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
                euroValue = (response.body()!!.rates.PLN * 100).roundToInt() / 100.0
                currentDate = response.body()!!.date
                val allRatesDupa = response.body()!!.rates
                allRates = allRatesDupa
                for (currency in Rates::class.java.declaredFields) {
                    val currencyName = currency.toString().substringAfterLast(".")
                    val currencyValue: Double = getRateForCurrency(currencyName, allRatesDupa)
                    //Log.d(TAG, "XXX $currencyName $currencyValue")
                    mapCurrencies.value += mapOf(currencyName to (((response.body()!!.rates.PLN / currencyValue) * 100).roundToInt() / 100.0))
                }
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

    private fun getRateForCurrency(currency: String, allRates: Rates) : Double {
       return when (currency) {
           "AED" -> allRates.AED
           "AFN" -> allRates.AFN
           "ANG" -> allRates.ANG
           "AOA" -> allRates.AOA
           "ARS" -> allRates.ARS
           "AUD" -> allRates.AUD
           "AWG" -> allRates.AWG
           "AZN" -> allRates.AZN
           "BAM" -> allRates.BAM
           "BBD" -> allRates.BBD
           "BND" -> allRates.BND
           "BOB" -> allRates.BOB
           "BRL" -> allRates.BRL
           "BSD" -> allRates.BSD
           "BTC" -> allRates.BTC
           "BTN" -> allRates.BTN
           "CAD" -> allRates.CAD
           "CDF" -> allRates.CDF
           "CHF" -> allRates.CHF
           "CLF" -> allRates.CLF
           "CLP" -> allRates.CLP
           "CNY" -> allRates.CNY
           "CUP" -> allRates.CUP
           "CVE" -> allRates.CVE
           "CZK" -> allRates.CZK
           "DJF" -> allRates.DJF
           "ERN" -> allRates.ERN
           "ETB" -> allRates.ETB
           //"val" -> allRates.val
           "FJD" -> allRates.FJD
           "FKP" -> allRates.FKP
           "GBP" -> allRates.GBP
           "GEL" -> allRates.GEL
           "GGP" -> allRates.GGP
           "GHS" -> allRates.GHS
           "HKD" -> allRates.HKD
           "HNL" -> allRates.HNL
           "HRK" -> allRates.HRK
           "HTG" -> allRates.HTG
           "HUF" -> allRates.HUF
           "IDR" -> allRates.IDR
           "ILS" -> allRates.ILS
           "IRR" -> allRates.IRR
           "ISK" -> allRates.ISK
           "JEP" -> allRates.JEP
           "JMD" -> allRates.JMD
           "JOD" -> allRates.JOD
           "JPY" -> allRates.JPY
           "KPW" -> allRates.KPW
           "KRW" -> allRates.KRW
           "KWD" -> allRates.KWD
           "KYD" -> allRates.KYD
           "KZT" -> allRates.KZT
           "LAK" -> allRates.LAK
           "LBP" -> allRates.LBP
           "LSL" -> allRates.LSL
           "LTL" -> allRates.LTL
           "LYD" -> allRates.LYD
           "MAD" -> allRates.MAD
           "MDL" -> allRates.MDL
           "MGA" -> allRates.MGA
           "MKD" -> allRates.MKD
           "MMK" -> allRates.MMK
           "MNT" -> allRates.MNT
           "MXN" -> allRates.MXN
           "MYR" -> allRates.MYR
           "MZN" -> allRates.MZN
           "NAD" -> allRates.NAD
           "NGN" -> allRates.NGN
           "NIO" -> allRates.NIO
           "NOK" -> allRates.NOK
           "NPR" -> allRates.NPR
           "PGK" -> allRates.PGK
           "PHP" -> allRates.PHP
           "PKR" -> allRates.PKR
           "PLN" -> allRates.PLN
           "PYG" -> allRates.PYG
           "QAR" -> allRates.QAR
           "RON" -> allRates.RON
           "RSD" -> allRates.RSD
           "RUB" -> allRates.RUB
           "RWF" -> allRates.RWF
           "SAR" -> allRates.SAR
           "SHP" -> allRates.SHP
           "SLL" -> allRates.SLL
           "SOS" -> allRates.SOS
           "SRD" -> allRates.SRD
           "STD" -> allRates.STD
           "SVC" -> allRates.SVC
           "SYP" -> allRates.SYP
           "SZL" -> allRates.SZL
           "THB" -> allRates.THB
           "TJS" -> allRates.TJS
           "TWD" -> allRates.TWD
           "TZS" -> allRates.TZS
           "UAH" -> allRates.UAH
           "UGX" -> allRates.UGX
           "USD" -> allRates.USD
           "UYU" -> allRates.UYU
           "UZS" -> allRates.UZS
           "VEF" -> allRates.VEF
           "VND" -> allRates.VND
           "VUV" -> allRates.VUV
           "WST" -> allRates.WST
           "XAF" -> allRates.XAF
           "XAG" -> allRates.XAG
           "XAU" -> allRates.XAU
           "XPF" -> allRates.XPF
           "YER" -> allRates.YER
           "ZAR" -> allRates.ZAR
           "ZMK" -> allRates.ZMK
           "ZMW" -> allRates.ZMW
           "ZWL" -> allRates.ZWL

           else -> 1.0
       }
}
}
