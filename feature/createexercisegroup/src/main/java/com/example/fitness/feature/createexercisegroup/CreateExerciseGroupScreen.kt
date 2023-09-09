package com.example.fitness.feature.createexercisegroup

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar

@Composable
internal fun CreateExerciseGroupRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateExerciseGroupViewModel = hiltViewModel()
) {
    val categoryName by viewModel.categoryName.collectAsStateWithLifecycle()

    CreateExerciseGroupScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        categoryName = categoryName,
        onCategoryNameChanged = { text -> viewModel.onCategoryNameChanged(text) },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateExerciseGroupScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    categoryName: String = "",
    onCategoryNameChanged: (String) -> Unit,
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
                    title = "New exercise group",
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
                    label = { Text(text = "Name") }
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
                text = "Create",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}