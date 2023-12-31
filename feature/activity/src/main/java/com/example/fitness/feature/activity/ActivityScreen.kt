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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ActivityRoute(
    modifier: Modifier = Modifier,
    onSaveCompletedExerciseClick: () -> Unit,
    onExerciseHistoryClick: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ActivityScreen(
        modifier = modifier,
        uiState = uiState,
        onSaveCompletedExerciseClick = onSaveCompletedExerciseClick,
        onExerciseHistoryClick = onExerciseHistoryClick,
    )
}

@Composable
internal fun ActivityScreen(
    modifier: Modifier = Modifier,
    uiState: ActivityUiState,
    onSaveCompletedExerciseClick: () -> Unit,
    onExerciseHistoryClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            when (uiState) {
                is ActivityUiState.Success -> {
                    item {
                        Spacer(
                            modifier = modifier
                                .height(16.dp),
                        )
                    }
                    item {
                        ActionTile(
                            title = stringResource(R.string.activity_activities_tile),
                            subtitle = stringResource(R.string.activity_activities_tile_content)
                                .format(uiState.activitiesCount),
                            onTileClick = onExerciseHistoryClick,
                        )
                        Spacer(
                            modifier = modifier
                                .height(12.dp),
                        )
                    }
                    item {
                        ActionTile(
                            title = stringResource(R.string.activity_average_score_tile),
                            subtitle = stringResource(R.string.activity_average_score_tile_content)
                                .format(uiState.averageScore),
                            onTileClick = {},
                        )
                        Spacer(
                            modifier = modifier
                                .height(12.dp),
                        )
                    }
                }

                is ActivityUiState.Loading ->
                    item {
                        Spacer(
                            modifier = modifier
                                .height(16.dp),
                        )
                    }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveCompletedExerciseClick() },
        ) {
            Text(
                text = stringResource(R.string.activity_add_activity_button),
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ActionTile(
    modifier: Modifier = Modifier,
    onTileClick: () -> Unit,
    title: String = "",
    subtitle: String = "",
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = { onTileClick() }
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            text = title
        )
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            text = subtitle
        )
    }
}
