package com.example.fitness.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitness.navigation.FitnessAppNavHost

@Composable
fun FitnessApp(
    appState: FitnessAppState = rememberFitnessAppState()
) {
    val navController = rememberNavController()

    // TODO: show/hide bottom bar
    val bottomBarVisibilityState = rememberSaveable {
        mutableStateOf(true)
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = bottomBarVisibilityState.value) {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    appState.topLevelDestination
                        .forEach { screen ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { navDestination ->
                                    navDestination.route?.contains(screen.name, true) ?: false
                                } ?: false,
                                onClick = { appState.navigateToTopLevelDestination(screen) },
                                icon = {
                                    Icon(
                                        imageVector = screen.imageVector,
                                        contentDescription = stringResource(screen.labelResId)
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(screen.labelResId)
                                    )
                                }
                            )
                        }
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