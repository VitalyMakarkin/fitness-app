package com.example.fitness.feature.createexercisecategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CreateExerciseCategoryRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateExerciseCategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.createExerciseCategoryUiState.collectAsStateWithLifecycle()
    val categoryName by viewModel.categoryName.collectAsStateWithLifecycle()
    val categoryDescription by viewModel.categoryDescription.collectAsStateWithLifecycle()
    val categoryContainsSets by viewModel.categoryContainsSets.collectAsStateWithLifecycle()
    val categoryContainsReps by viewModel.categoryContainsReps.collectAsStateWithLifecycle()
    val categoryContainsDuration by viewModel.categoryContainsDuration.collectAsStateWithLifecycle()

    CreateExerciseCategoryScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        categoryName = categoryName,
        onCategoryNameChanged = { text -> viewModel.onCategoryNameChanged(text) },
        categoryDescription = categoryDescription,
        onCategoryDescriptionChanged = { text -> viewModel.onCategoryDescriptionChanged(text) },
        categoryContainsSets = categoryContainsSets,
        onCategoryContainsSetsChanged = { checked -> viewModel.onCategoryContainsSetsChanged(checked) },
        categoryContainsReps = categoryContainsReps,
        onCategoryContainsRepsChanged = { checked -> viewModel.onCategoryContainsRepsChanged(checked) },
        categoryContainsDuration = categoryContainsDuration,
        onCategoryContainsDurationChanged = { checked ->
            viewModel.onCategoryContainsDurationChanged(
                checked
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateExerciseCategoryScreen(
    modifier: Modifier = Modifier,
    uiState: CreateExerciseCategoryUiState,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    categoryName: String = "",
    onCategoryNameChanged: (String) -> Unit,
    categoryDescription: String = "",
    onCategoryDescriptionChanged: (String) -> Unit,
    categoryContainsSets: Boolean = false,
    onCategoryContainsSetsChanged: (Boolean) -> Unit,
    categoryContainsReps: Boolean = false,
    onCategoryContainsRepsChanged: (Boolean) -> Unit,
    categoryContainsDuration: Boolean = false,
    onCategoryContainsDurationChanged: (Boolean) -> Unit,
) {
    Column {
        Text(
            text = "[Back]",
            modifier = modifier.clickable { onBackClick() }
        )
        TextField(
            value = categoryName,
            onValueChange = { text -> onCategoryNameChanged(text) },
            label = { Text(text = "Name") }
        )
        TextField(
            value = categoryDescription,
            onValueChange = { text -> onCategoryDescriptionChanged(text) },
            label = { Text(text = "Description") }
        )
        Checkbox(
            checked = categoryContainsSets,
            onCheckedChange = { checked -> onCategoryContainsSetsChanged(checked) },
        )
        Checkbox(
            checked = categoryContainsReps,
            onCheckedChange = { checked -> onCategoryContainsRepsChanged(checked) },
        )
        Checkbox(
            checked = categoryContainsDuration,
            onCheckedChange = { checked -> onCategoryContainsDurationChanged(checked) },
        )
        when (uiState) {
            is CreateExerciseCategoryUiState.Success -> Text(
                text = "CreateExerciseCategory: [Create]",
                modifier.clickable { onCreate() }
            )

            is CreateExerciseCategoryUiState.Loading -> Text(
                text = "CreateExerciseCategory: Loading!"
            )
        }
    }
}