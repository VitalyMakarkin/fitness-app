package com.example.fitness.feature.createscheduledevent.dialog.exercisegroupselection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.feature.createscheduledevent.R

@Composable
internal fun ExerciseGroupSelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseGroupClicked: (ExerciseGroup) -> Unit,
    viewModel: ExerciseGroupSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExerciseGroupSelectionDialog(
        modifier = modifier,
        onDismiss = onDismiss,
        onExerciseGroupClicked = onExerciseGroupClicked,
        uiState = uiState
    )
}

@Composable
internal fun ExerciseGroupSelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseGroupClicked: (ExerciseGroup) -> Unit,
    uiState: ExerciseGroupSelectionUiState
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    TopNavigationBar(
                        title = stringResource(R.string.exercise_group_selection_top_navigation_bar_title),
                        onBackClick = { onDismiss() }
                    )
                }
                if (uiState is ExerciseGroupSelectionUiState.Success) {
                    items(uiState.groups) { group ->
                        ExerciseGroupListItem(
                            modifier = modifier.clickable {
                                onExerciseGroupClicked(group)
                                onDismiss()
                            },
                            name = group.name
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun ExerciseGroupListItem(
    modifier: Modifier = Modifier,
    name: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight(600)
        )
    }
}
