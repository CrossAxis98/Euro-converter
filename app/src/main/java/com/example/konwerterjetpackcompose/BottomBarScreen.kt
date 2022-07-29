package com.example.konwerterjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeEurPl : BottomBarScreen(
        route = "homeEurPl",
        title = "Home",
        icon = Icons.Default.Home

    )
    object CalculatorEurPl : BottomBarScreen(
        route = "calculatorEurPl",
        title = "Kalkulator",
        icon = Icons.Default.ArrowDropDown
    )
}