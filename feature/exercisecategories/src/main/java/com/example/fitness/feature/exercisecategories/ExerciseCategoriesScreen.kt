package com.example.fitness.feature.exercisecategories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.example.fitness.feature.exercisecategories.model.ExerciseCategoryUI

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
                        ExerciseCategoryTile(
                            modifier = modifier,
                            exerciseCategory = category
                        )
                        Spacer(modifier = modifier.height(12.dp))
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
internal fun ExerciseCategoryTile(
    modifier: Modifier = Modifier,
    exerciseCategory: ExerciseCategoryUI
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(800), // TODO: move to theme typography
            text = exerciseCategory.name
        )

        if (exerciseCategory.description != null) {
            Text(
                modifier = modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                fontSize = 20.sp, // TODO: move to theme typography
                fontWeight = FontWeight(400), // TODO: move to theme typography
                text = exerciseCategory.description
            )
        }

        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(400), // TODO: move to theme typography
            text = exerciseCategory.additionalInfo
        )
    }
}