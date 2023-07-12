package com.example.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitness.core.model.Exercise
import com.example.fitness.ui.theme.FitnessTheme
import dagger.hilt.android.AndroidEntryPoint

// TODO: Move
sealed class Screen(val route: String, val imageVector: ImageVector, val label: String) {
    object Home : Screen("home", Icons.Default.Home, "Home")
    object Schedule : Screen("schedule", Icons.Default.List, "Schedule")
    object Settings : Screen("settings", Icons.Default.Settings, "Settings")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider {
                FitnessTheme {
                    FitnessApp()
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