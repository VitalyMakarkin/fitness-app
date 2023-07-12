package com.example.fitness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberFitnessAppState(
    navHostController: NavHostController = rememberNavController()
): FitnessAppState {
    return remember(navHostController) {
        FitnessAppState(navHostController)
    }
}

@Stable
class FitnessAppState(
    val navHostController: NavHostController
) {
    // TODO
}