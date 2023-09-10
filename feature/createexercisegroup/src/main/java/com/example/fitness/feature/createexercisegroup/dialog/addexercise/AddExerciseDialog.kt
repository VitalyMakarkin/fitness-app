package com.example.fitness.feature.createexercisegroup.dialog.addexercise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar

@Composable
internal fun AddExerciseDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModel: AddExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.addExerciseUiState.collectAsStateWithLifecycle()

    AddExerciseDialog(
        modifier = modifier,
        onDismiss = onDismiss,
        uiState = uiState,
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun AddExerciseDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    uiState: AddExerciseUiState,
    shouldNavigateBack: Boolean = false
) {
    LaunchedEffect(shouldNavigateBack) {
        if (shouldNavigateBack) {
            onDismiss()
        }
    }

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
                        title = "Add exercise",
                        onBackClick = { onDismiss() }
                    )
                }
//                if (uiState is AddExerciseUiState) {
//
//                }
            }
        }
    }
}