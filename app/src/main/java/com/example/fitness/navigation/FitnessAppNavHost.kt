package com.example.fitness.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.fitness.ui.FitnessAppState
import com.example.fitness.feature.exercisehistory.navigation.exerciseHistoryScreen
import com.example.fitness.feature.exercisehistory.navigation.exerciseHistoryRoute

@Composable
fun FitnessAppNavHost(
    appState: FitnessAppState,
    modifier: Modifier = Modifier,
    startDestination: String = exerciseHistoryRoute
) {
    val navController = appState.navHostController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        exerciseHistoryScreen()
    }
}