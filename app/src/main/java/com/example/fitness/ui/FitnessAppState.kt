package com.example.fitness.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val currentDestination: NavDestination?
        @Composable get() = navHostController
            .currentBackStackEntryAsState().value?.destination
}