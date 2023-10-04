package com.example.fitness.feature.schedule.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.schedule.ScheduleRoute

const val scheduleRoute = "schedule"

fun NavController.navigateToSchedule() {
    this.navigate(scheduleRoute)
}

fun NavGraphBuilder.scheduleScreen(
    onCreateScheduledEventClick: () -> Unit,
    nestedScreens: NavGraphBuilder.() -> Unit,
) {
    composable(
        route = scheduleRoute
    ) {
        ScheduleRoute(
            onCreateScheduledEventClick = onCreateScheduledEventClick
        )
    }
    nestedScreens()
}
