package com.example.fitness.feature.createexercisegroup.dialog.exercisecategoryselection

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
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.feature.createexercisegroup.R

@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseCategoryClicked: (ExerciseCategory) -> Unit,
    viewModel: ExerciseCategorySelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExerciseCategorySelectionDialog(
        modifier = modifier,
        onDismiss = onDismiss,
        onExerciseCategoryClicked = onExerciseCategoryClicked,
        uiState = uiState
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseCategoryClicked: (ExerciseCategory) -> Unit,
    uiState: ExerciseCategorySelectionUiState
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
                        title = stringResource(R.string.category_selection_dialog_top_navigation_bar_title),
                        onBackClick = { onDismiss() }
                    )
                }
                if (uiState is ExerciseCategorySelectionUiState.Success) {
                    items(uiState.categories) { category ->
                        ExerciseCategoriesListItem(
                            modifier = modifier.clickable {
                                onExerciseCategoryClicked(category)
                                onDismiss()
                            },
                            name = category.name,
                            description = category.description ?: ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun ExerciseCategoriesListItem(
    modifier: Modifier = Modifier,
    name: String = "",
    description: String = "",
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

        if (description.isNotEmpty()) {
            Text(
                text = description,
                fontSize = 20.sp,
                fontWeight = FontWeight(400)
            )
        }
    }
}
