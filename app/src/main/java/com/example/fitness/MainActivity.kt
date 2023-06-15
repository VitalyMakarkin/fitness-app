package com.example.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.domain.models.Exercise
import com.example.fitness.ui.theme.FitnessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainUiState: MainUiState by viewModel.mainUiState.collectAsStateWithLifecycle()

                    when (mainUiState) {
                        is MainUiState.Success -> CompletedExercisesList(exercises = (mainUiState as MainUiState.Success).completedExercises)
                        else -> Text(text = "Empty")
                    }
                }
            }
        }
    }
}

@Composable
fun CompletedExercisesList(exercises: List<Exercise>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(exercises) { exercise ->
            Text(text = "${exercise.id}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedExercisesList() {
    FitnessTheme {
        CompletedExercisesList(
            listOf(
                Exercise(1, 1, 1L),
                Exercise(2, 2, 1L)
            )
        )
    }
}