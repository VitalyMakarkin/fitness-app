package com.example.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.model.Exercise
import com.example.fitness.ui.theme.FitnessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitnessTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = true,
                                onClick = { /*TODO*/ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = null
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = null
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .clickable { viewModel.addExercise() }
                            .fillMaxSize(),
                    ) {
                        val mainUiState: MainUiState by viewModel.mainUiState.collectAsStateWithLifecycle()

                        when (mainUiState) {
                            is MainUiState.Success -> ExercisesList(
                                exercises = (mainUiState as MainUiState.Success).completedExercises
                            )

                            else -> Text(
                                text = "Empty"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExercisesList(
    modifier: Modifier = Modifier,
    exercises: List<Exercise>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(exercises) { exercise ->
            ExerciseItem(exercise = exercise)
        }
    }
}

@Composable
fun ExerciseItem(modifier: Modifier = Modifier, exercise: Exercise) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "${exercise.id}")
        Text(text = "${exercise.completedAt}")
    }
}

@Preview(showBackground = true)
@Composable
fun ExercisesListPreview() {
    FitnessTheme {
        ExercisesList(
            exercises = listOf(
                Exercise(1, 10, 1L, 1L, null, null, null, 0),
                Exercise(2, 22, 1L, 1L, null, null, null, 0),
                Exercise(3, 33, 1L, 1L, null, null, null, 0),
            )
        )
    }
}