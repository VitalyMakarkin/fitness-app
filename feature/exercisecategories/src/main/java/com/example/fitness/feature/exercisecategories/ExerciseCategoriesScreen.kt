package com.example.fitness.feature.exercisecategories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExerciseCategoriesScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onNewExerciseCategoryCreateClick = onNewExerciseCategoryCreateClick,
        onUpdate = { viewModel.updateRemoteExerciseCategories() },
        onDeleteItem = { id -> viewModel.deleteExerciseCategory(id) }
    )
}

@Composable
internal fun ExerciseCategoriesScreen(
    modifier: Modifier = Modifier,
    uiState: ExerciseCategoriesUiState,
    onBackClick: () -> Unit,
    onNewExerciseCategoryCreateClick: () -> Unit,
    onUpdate: () -> Unit,
    onDeleteItem: (Long) -> Unit,
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
                    title = stringResource(R.string.exercise_categories_top_navigation_bar_title),
                    onBackClick = onBackClick,
                    menuVisibility = true,
                    onMenuClick = onUpdate
                )
            }

            when (uiState) {
                is ExerciseCategoriesUiState.Success -> {
                    items(uiState.exerciseCategories) { category ->
                        ExerciseCategoryTile(
                            modifier = modifier,
                            exerciseCategory = category,
                            delete = onDeleteItem
                        )
                        Spacer(modifier = modifier.height(12.dp))
                    }
                }

                is ExerciseCategoriesUiState.Loading -> {}
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onNewExerciseCategoryCreateClick() }
        ) {
            Text(
                text = stringResource(R.string.exercise_categories_add_category_button),
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ExerciseCategoryTile(
    modifier: Modifier = Modifier,
    exerciseCategory: ExerciseCategoryUI,
    delete: (Long) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = modifier,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                text = exerciseCategory.name
            )
            IconButton(onClick = { delete(exerciseCategory.id) }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Delete"
                )
            }
        }

        Text(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            text = exerciseCategory.description ?: ""
        )
    }
}
