package com.example.fitness.feature.createscheduledevent

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.feature.createscheduledevent.dialog.exercisegroupselection.ExerciseGroupSelectionDialog

@Composable
internal fun CreateScheduledEventRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateScheduledEventViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val eventScheduledAt by viewModel.scheduledAt.collectAsStateWithLifecycle()

    CreateScheduledEventScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        eventScheduledAt = eventScheduledAt.toString(),
        onEventScheduledAtChanged = { text -> viewModel.onEventScheduledAtChanged(text) },
        onEventExerciseGroupChanged = { group -> viewModel.changeExerciseGroup(group) },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateScheduledEventScreen(
    modifier: Modifier = Modifier,
    uiState: CreateScheduledEventUiState,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    eventScheduledAt: String = "",
    onEventScheduledAtChanged: (String) -> Unit,
    onEventExerciseGroupChanged: (ExerciseGroup) -> Unit,
    shouldNavigateBack: Boolean = false
) {
    var showExerciseGroupSelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showExerciseGroupSelectionDialog) {
        ExerciseGroupSelectionDialog(
            onDismiss = { showExerciseGroupSelectionDialog = false },
            onExerciseGroupClicked = { group -> onEventExerciseGroupChanged(group) })
    }

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
                    title = stringResource(R.string.create_scheduled_event_top_navigation_bar_title),
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
                    label = { Text(text = stringResource(R.string.create_scheduled_event_text_input_scheduled_at_label)) }
                )
            }
            item {
                when (uiState) {
                    is CreateScheduledEventUiState.Success ->
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = uiState.selectedExerciseGroup?.name
                                ?: stringResource(R.string.create_scheduled_event_not_selected_category),
                            onValueChange = { },
                            label = { Text(text = stringResource(R.string.create_scheduled_event_text_input_exercise_group_label)) },
                            readOnly = true,
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                showExerciseGroupSelectionDialog = true
                                            }
                                        }
                                    }
                                }
                        )

                    is CreateScheduledEventUiState.Loading -> {}
                }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onCreate() }
        ) {
            Text(
                text = stringResource(R.string.create_scheduled_event_confirm_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}