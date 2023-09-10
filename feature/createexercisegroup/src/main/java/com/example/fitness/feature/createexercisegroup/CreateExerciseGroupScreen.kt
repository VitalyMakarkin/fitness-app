package com.example.fitness.feature.createexercisegroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.feature.createexercisegroup.dialog.addexercise.AddExerciseDialog

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
    var showAddExerciseDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showAddExerciseDialog) {
        AddExerciseDialog(
            onDismiss = { showAddExerciseDialog = false }
        )
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
            item {
                IconButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { showAddExerciseDialog = true }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add"
                        )
                        Spacer(
                            modifier = modifier.width(4.dp)
                        )
                        Text(
                            text = "Add exercise",
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                    }
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
                text = "Create",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}