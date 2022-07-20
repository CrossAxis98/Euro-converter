package com.example.konwerterjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.konwerterjetpackcompose.screens.AllCurrencies
import com.example.konwerterjetpackcompose.screens.CalculatorEurPl
import com.example.konwerterjetpackcompose.screens.HomeEurPl

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.HomeEurPl.route
    ) {
         composable(route = BottomBarScreen.HomeEurPl.route) {
             HomeEurPl()
         }
        composable(route = BottomBarScreen.AllCurrencies.route) {
            AllCurrencies()
        }
        composable(route = BottomBarScreen.CalculatorEurPl.route) {
            CalculatorEurPl()
        }
    }
}