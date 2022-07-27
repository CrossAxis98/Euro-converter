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
import com.example.konwerterjetpackcompose.screens.allRates
import com.example.konwerterjetpackcompose.screens.currentDate
import com.example.konwerterjetpackcompose.screens.euroValue
import com.example.konwerterjetpackcompose.screens.mapCurrencies
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
                allRates = response.body()!!.rates
                for (currency in Rates::class.java.declaredFields) {
                    val currencyName = currency.toString().substringAfterLast(".")
                    val currencyValue: Double? = getRateForCurrency(currencyName)
                    //Log.d(TAG, "XXX $currencyName $currencyValue")
                    mapCurrencies.value += mapOf(currencyName to currencyValue)
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

    private fun getRateForCurrency(currency: String) : Double? {
       return when (currency) {
            "AED" -> allRates?.AED
            "AFN" -> allRates?.AFN
            "ALL" -> allRates?.ALL
            "AMD" -> allRates?.AMD
            "ANG" -> allRates?.ANG
            "AOA" -> allRates?.AOA
            "ARS" -> allRates?.ARS
            "AUD" -> allRates?.AUD
            "AWG" -> allRates?.AWG
            "AZN" -> allRates?.AZN
            "BAM" -> allRates?.BAM
            "BBD" -> allRates?.BBD
            "BDT" -> allRates?.BDT
            "BGN" -> allRates?.BGN
            "BHD" -> allRates?.BHD
            "BIF" -> allRates?.BIF
            "BMD" -> allRates?.BMD
            "BND" -> allRates?.BND
            "BOB" -> allRates?.BOB
            "BRL" -> allRates?.BRL
            "BSD" -> allRates?.BSD
            "BTC" -> allRates?.BTC
            "BTN" -> allRates?.BTN
            "BWP" -> allRates?.BWP
            "BYN" -> allRates?.BYN
            "BYR" -> allRates?.BYR
            "BZD" -> allRates?.BZD
            "CAD" -> allRates?.CAD
            "CDF" -> allRates?.CDF
            "CHF" -> allRates?.CHF
            "CLF" -> allRates?.CLF
            "CLP" -> allRates?.CLP
            "CNY" -> allRates?.CNY
            "COP" -> allRates?.COP
            "CRC" -> allRates?.CRC
            "CUC" -> allRates?.CUC
            "CUP" -> allRates?.CUP
            "CVE" -> allRates?.CVE
            "CZK" -> allRates?.CZK
            "DJF" -> allRates?.DJF
            "DKK" -> allRates?.DKK
            "DOP" -> allRates?.DOP
            "DZD" -> allRates?.DZD
            "EGP" -> allRates?.EGP
            "ERN" -> allRates?.ERN
            "ETB" -> allRates?.ETB
            //euro
            "FJD" -> allRates?.FJD
            "FKP" -> allRates?.FKP
            "GBP" -> allRates?.GBP
            "GEL" -> allRates?.GEL
            "GGP" -> allRates?.GGP
            "GHS" -> allRates?.GHS
            "GIP" -> allRates?.GIP
            "GMD" -> allRates?.GMD
            "GNF" -> allRates?.GNF
            "GTQ" -> allRates?.GTQ
            "GYD" -> allRates?.GYD
            "HKD" -> allRates?.HKD
            "HNL" -> allRates?.HNL
            "HRK" -> allRates?.HRK
            "HTG" -> allRates?.HTG
            "HUF" -> allRates?.HUF
            "IDR" -> allRates?.IDR
            "ILS" -> allRates?.ILS
            "IMP" -> allRates?.IMP
            "INR" -> allRates?.INR
            "IQD" -> allRates?.IQD
            "IRR" -> allRates?.IRR
            "ISK" -> allRates?.ISK
            "JEP" -> allRates?.JEP
            "JMD" -> allRates?.JMD
            "JOD" -> allRates?.JOD
            "JPY" -> allRates?.JPY
            "KES" -> allRates?.KES
            "KGS" -> allRates?.KGS
            "KHR" -> allRates?.KHR
            "KMF" -> allRates?.KMF
            "KPW" -> allRates?.KPW
            "KRW" -> allRates?.KRW
            "KWD" -> allRates?.KWD
            "KYD" -> allRates?.KYD
            "KZT" -> allRates?.KZT
            "LAK" -> allRates?.LAK
            "LBP" -> allRates?.LBP
            "LKR" -> allRates?.LKR
            "LRD" -> allRates?.LRD
            "LSL" -> allRates?.LSL
            "LTL" -> allRates?.LTL
            "LVL" -> allRates?.LVL
            "LYD" -> allRates?.LYD
            "MAD" -> allRates?.MAD
            "MDL" -> allRates?.MDL
            "MGA" -> allRates?.MGA
            "MKD" -> allRates?.MKD
            "MMK" -> allRates?.MMK
            "MNT" -> allRates?.MNT
            "MOP" -> allRates?.MOP
            "MRO" -> allRates?.MRO
            "MUR" -> allRates?.MUR
            "MVR" -> allRates?.MVR
            "MWK" -> allRates?.MWK
            "MXN" -> allRates?.MXN
            "MYR" -> allRates?.MYR
            "MZN" -> allRates?.MZN
            "NAD" -> allRates?.NAD
            "NGN" -> allRates?.NGN
            "NIO" -> allRates?.NIO
            "NOK" -> allRates?.NOK
            "NPR" -> allRates?.NPR
            "NZD" -> allRates?.NZD
            "OMR" -> allRates?.OMR
            "PAB" -> allRates?.PAB
            "PEN" -> allRates?.PEN
            "PGK" -> allRates?.PGK
            "PHP" -> allRates?.PHP
            "PKR" -> allRates?.PKR
            //"PLN" -> allRates?.PLN
            "PYG" -> allRates?.PYG
            "QAR" -> allRates?.QAR
            "RON" -> allRates?.RON
            "RSD" -> allRates?.RSD
            "RUB" -> allRates?.RUB
            "RWF" -> allRates?.RWF
            "SAR" -> allRates?.SAR
            "SBD" -> allRates?.SBD
            "SCR" -> allRates?.SCR
            "SDG" -> allRates?.SDG
            "SEK" -> allRates?.SEK
            "SGD" -> allRates?.SGD
            "SHP" -> allRates?.SHP
            "SLL" -> allRates?.SLL
            "SOS" -> allRates?.SOS
            "SRD" -> allRates?.SRD
            "STD" -> allRates?.STD
            "SVC" -> allRates?.SVC
            "SYP" -> allRates?.SYP
            "SZL" -> allRates?.SZL
            "THB" -> allRates?.THB
            "TJS" -> allRates?.TJS
            "TMT" -> allRates?.TMT
            "TND" -> allRates?.TND
            "TOP" -> allRates?.TOP
            "TRY" -> allRates?.TRY
            "TTD" -> allRates?.TTD
            "TWD" -> allRates?.TWD
            "TZS" -> allRates?.TZS
            "UAH" -> allRates?.UAH
            "UGX" -> allRates?.UGX
            "USD" -> allRates?.USD
            "UYU" -> allRates?.UYU
            "UZS" -> allRates?.UZS
            "VEF" -> allRates?.VEF
            "VND" -> allRates?.VND
            "VUV" -> allRates?.VUV
            "WST" -> allRates?.WST
            "XAF" -> allRates?.XAF
            "XAG" -> allRates?.XAG
            "XAU" -> allRates?.XAU
            "XCD" -> allRates?.XCD
            "XDR" -> allRates?.XDR
            "XOF" -> allRates?.XOF
            "XPF" -> allRates?.XPF
            "YER" -> allRates?.YER
            "ZAR" -> allRates?.ZAR
            "ZMK" -> allRates?.ZMK
            "ZMW" -> allRates?.ZMW
            "ZWL" -> allRates?.ZWL

           else -> 1.0
       }
}
}
