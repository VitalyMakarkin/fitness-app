package com.example.fitness.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitness.navigation.FitnessAppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessApp(
    appState: FitnessAppState = rememberFitnessAppState()
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                appState.topLevelDestination
                    .forEach { screen ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = { appState.navigateToTopLevelDestination(screen) },
                            icon = {
                                Icon(
                                    imageVector = screen.imageVector,
                                    contentDescription = screen.route
                                )
                            },
                            label = {
                                Text(
                                    text = screen.label
                                )
                            }
                        )
                    }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            FitnessAppNavHost(
                appState = appState
            )
        }
    }
}