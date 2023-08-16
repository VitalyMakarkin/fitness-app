package com.example.fitness.feature.schedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.schedule.ScheduleRoute

const val scheduleRoute = "schedule"

fun NavGraphBuilder.scheduleScreen() {
    composable(
        route = scheduleRoute
    ) {
        ScheduleRoute()
    }
}