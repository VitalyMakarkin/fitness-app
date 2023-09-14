package com.example.fitness.feature.createexercisecategory

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
internal fun CreateExerciseCategoryRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateExerciseCategoryViewModel = hiltViewModel()
) {
    val categoryName by viewModel.categoryName.collectAsStateWithLifecycle()
    val categoryDescription by viewModel.categoryDescription.collectAsStateWithLifecycle()

    CreateExerciseCategoryScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        categoryName = categoryName,
        onCategoryNameChanged = { text -> viewModel.onCategoryNameChanged(text) },
        categoryDescription = categoryDescription,
        onCategoryDescriptionChanged = { text -> viewModel.onCategoryDescriptionChanged(text) },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateExerciseCategoryScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    categoryName: String = "",
    onCategoryNameChanged: (String) -> Unit,
    categoryDescription: String = "",
    onCategoryDescriptionChanged: (String) -> Unit,
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
                    title = stringResource(R.string.create_exercise_category_top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = categoryName,
                    onValueChange = { text -> onCategoryNameChanged(text) },
                    label = { Text(text = stringResource(R.string.create_exercise_category_text_input_name_label)) }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = categoryDescription,
                    onValueChange = { text -> onCategoryDescriptionChanged(text) },
                    label = { Text(text = stringResource(R.string.create_exercise_category_text_input_description_label)) }
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
                text = stringResource(R.string.create_exercise_category_create_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}