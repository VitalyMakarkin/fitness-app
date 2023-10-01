package com.example.fitness.feature.exercisehistory

import com.example.fitness.core.testing.data.ExercisesData
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

class ExerciseHistoryViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ExerciseHistoryInteractor

    private lateinit var viewModel: ExerciseHistoryViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ExerciseHistoryViewModel(
            interactor = interactor,
        )
    }

    @Test
    fun testUiStateLoading() = runTest {
        assertEquals(
            ExerciseHistoryUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun testUiStateSuccess() = runTest {
        val exercises = ExercisesData
        `when`(interactor.observeExercises())
            .thenReturn(flowOf(exercises))

        verify(interactor).observeExercises()
    }

    @Test
    fun testDeleteExercise() = runTest {
        viewModel.deleteExercise(0)

        verify(interactor).deleteExercise(0)
    }
}