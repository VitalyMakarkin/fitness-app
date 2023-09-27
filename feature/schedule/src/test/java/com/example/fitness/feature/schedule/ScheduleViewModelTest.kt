package com.example.fitness.feature.schedule

import com.example.fitness.core.testing.data.ScheduledExerciseEventsData
import com.example.fitness.core.testing.utils.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.verify

class ScheduleViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ScheduleInteractor

    private lateinit var viewModel: ScheduleViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ScheduleViewModel(
            interactor = interactor,
        )
    }

    @Test
    fun testUiStateLoading() = runTest {
        assertEquals(
            ScheduleUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun testUiStateSuccess() = runTest {
        val events = ScheduledExerciseEventsData

        `when`(interactor.observeEvents())
            .thenReturn(flowOf(events))

        verify(interactor).observeEvents()
    }
}