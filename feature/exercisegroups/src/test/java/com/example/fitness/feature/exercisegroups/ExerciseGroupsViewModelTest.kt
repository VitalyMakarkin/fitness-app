package com.example.fitness.feature.exercisegroups

import com.example.fitness.core.testing.data.ExerciseGroupsData
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

class ExerciseGroupsViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ExerciseGroupsInteractor

    private lateinit var viewModel: ExerciseGroupsViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ExerciseGroupsViewModel(
            interactor = interactor
        )
    }

    @Test
    fun testUiStateLoading() = runTest {
        assertEquals(
            ExerciseGroupsUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun testUiStateSuccess() = runTest {
        val groups = ExerciseGroupsData
        `when`(interactor.observeExerciseGroups())
            .thenReturn(flowOf(groups))

        verify(interactor).observeExerciseGroups()
    }

    @Test
    fun testDeleteExerciseGroup() = runTest {
        viewModel.deleteExerciseGroup(0)

        verify(interactor).deleteExerciseGroup(0)
    }
}