package com.example.fitness.feature.createscheduledevent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar

@Composable
internal fun CreateScheduledEventRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateScheduledEventViewModel = hiltViewModel()
) {
    val eventScheduledAt by viewModel.scheduledAt.collectAsStateWithLifecycle()
    val eventExerciseGroupId by viewModel.exerciseGroupId.collectAsStateWithLifecycle()

    CreateScheduledEventScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        eventScheduledAt = eventScheduledAt.toString(),
        onEventScheduledAtChanged = { text -> viewModel.onEventScheduledAtChanged(text) },
        eventExerciseGroupId = eventExerciseGroupId.toString(),
        onEventExerciseGroupChanged = { text -> viewModel.onEventExerciseGroupChanged(text) },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateScheduledEventScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    eventScheduledAt: String = "",
    onEventScheduledAtChanged: (String) -> Unit,
    eventExerciseGroupId: String = "",
    onEventExerciseGroupChanged: (String) -> Unit,
    shouldNavigateBack: Boolean = false
) {
    LaunchedEffect(shouldNavigateBack) {
        if (shouldNavigateBack) {
            onBackClick()
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                TopNavigationBar(
                    title = stringResource(R.string.top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = eventScheduledAt,
                    onValueChange = { text -> onEventScheduledAtChanged(text) },
                    label = { Text(text = stringResource(R.string.text_input_scheduled_at_label)) }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = eventExerciseGroupId,
                    onValueChange = { text -> onEventExerciseGroupChanged(text) },
                    label = { Text(text = stringResource(R.string.text_input_exercise_group_label)) }
                )
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onCreate() }
        ) {
            Text(
                text = stringResource(R.string.confirm_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}