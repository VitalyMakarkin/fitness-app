package com.example.fitness.feature.schedule

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.feature.schedule.model.ScheduledEventUI
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun ScheduleRoute(
    modifier: Modifier = Modifier,
    onCreateScheduledEventClick: () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScheduleScreen(
        uiState = uiState,
        onCreateScheduledEventClick = onCreateScheduledEventClick,
        modifier = modifier
    )
}

@Composable
internal fun ScheduleScreen(
    uiState: ScheduleUiState,
    onCreateScheduledEventClick: () -> Unit,
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
                is ScheduleUiState.Success -> {
                    item {
                        Spacer(modifier = modifier.height(16.dp))
                    }

                    items(uiState.events) { event ->
                        ScheduledEventTile(
                            modifier = modifier,
                            event = event
                        )
                        Spacer(modifier = modifier.height(12.dp))
                    }
                }

                is ScheduleUiState.Loading -> {}
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onCreateScheduledEventClick() }
        ) {
            Text(
                text = stringResource(R.string.schedule_add_event_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ScheduledEventTile(
    modifier: Modifier = Modifier,
    event: ScheduledEventUI
) {
    val formatter = SimpleDateFormat("dd.MM.yy", Locale.ROOT)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            text = event.exerciseGroupName
        )

        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            text = formatter.format(event.scheduledAt)
        )
    }
}