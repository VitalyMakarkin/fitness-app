package com.example.fitness.feature.exercisecategories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseCategory
import java.lang.StringBuilder

@Composable
internal fun ExerciseCategoriesRouter(
    onBackClick: () -> Unit,
    onNewExerciseCategoryCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseCategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseCategoriesUiState.collectAsStateWithLifecycle()

    ExerciseCategoriesScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onNewExerciseCategoryCreateClick = onNewExerciseCategoryCreateClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseCategoriesScreen(
    uiState: ExerciseCategoriesUiState,
    onBackClick: () -> Unit,
    onNewExerciseCategoryCreateClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            item {
                TopNavigationBar(
                    title = "Exercise categories",
                    onBackClick = onBackClick
                )
            }

            when (uiState) {
                is ExerciseCategoriesUiState.Success -> {
                    items(uiState.exerciseCategories) { category ->
                        ExerciseCategoriesListItem(
                            name = category.name,
                            description = category.description ?: "",
                            containsSets = category.containsSets,
                            containsReps = category.containsReps,
                            containsDuration = category.containsDuration
                        )
                    }
                }

                is ExerciseCategoriesUiState.Loading -> item {
                    Text(
                        text = "ExerciseCategories: Loading!"
                    )
                }
            }
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onNewExerciseCategoryCreateClick() }
        ) {
            Text(
                text = "Add category",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ExerciseCategoriesListItem(
    modifier: Modifier = Modifier,
    name: String = "",
    description: String = "",
    containsSets: ExerciseCategory.RequiredState,
    containsReps: ExerciseCategory.RequiredState,
    containsDuration: ExerciseCategory.RequiredState
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

        Text(
            text = convertSubtitle(containsSets, containsReps, containsDuration),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
    }
}

// TODO: move to presentation model
internal fun convertSubtitle(
    containsSets: ExerciseCategory.RequiredState,
    containsReps: ExerciseCategory.RequiredState,
    containsDuration: ExerciseCategory.RequiredState
): String {
    val required = mutableListOf<String>()
    val optional = mutableListOf<String>()

    when (containsSets) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("sets")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("sets")
        else -> {}
    }

    when (containsReps) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("reps")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("reps")
        else -> {}
    }

    when (containsDuration) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("duration")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("duration")
        else -> {}
    }

    return StringBuilder()
        .apply {
            if (required.isEmpty() && optional.isEmpty()) {
                append("without recommendations")
            } else {
                if (required.isNotEmpty()) {
                    append("required: ${required.joinToString(", ")}")
                    if (optional.isNotEmpty()) {
                        append(" ")
                    }
                }
                if (optional.isNotEmpty()) {
                    append("optional: ${optional.joinToString(", ")}")
                }
            }
        }
        .toString()
}