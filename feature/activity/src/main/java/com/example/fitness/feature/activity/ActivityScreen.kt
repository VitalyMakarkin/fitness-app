package com.example.fitness.feature.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
internal fun ActivityRoute(
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.activityUiState.collectAsStateWithLifecycle()

    ActivityScreen(
        uiState = uiState,
        onSaveCompletedExerciseClick = onSaveCompletedExerciseClick,
        modifier = modifier
    )
}

@Composable
internal fun ActivityScreen(
    uiState: ActivityUiState,
    onSaveCompletedExerciseClick: () -> Unit,
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
            when (uiState) {
                is ActivityUiState.Success -> {

                    item {
                        Spacer(modifier = modifier.height(12.dp))
                    }

                    item {
                        ActionTile(
                            title = "Activities",
                            subtitle = "${uiState.activitiesCount} exercises for all time"
                        )
                    }
                }

                is ActivityUiState.Loading ->
                    item {
                        Text(
                            text = "Loading!"
                        )
                    }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveCompletedExerciseClick() }
        ) {
            Text(
                text = "Add completed exercise",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ActionTile(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(800), // TODO: move to theme typography
            text = title
        )
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(400), // TODO: move to theme typography
            text = subtitle
        )
    }
}