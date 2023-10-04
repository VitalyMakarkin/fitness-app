package com.example.fitness.feature.createscheduledevent.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.createscheduledevent.CreateScheduledEventRouter

const val createScheduledEventRoute = "create_scheduled_event"

fun NavController.navigateToCreateScheduledEvent() {
    this.navigate(createScheduledEventRoute)
}

fun NavGraphBuilder.createScheduledEventScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = createScheduledEventRoute
    ) {
        CreateScheduledEventRouter(
            onBackClick = onBackClick
        )
    }
}
