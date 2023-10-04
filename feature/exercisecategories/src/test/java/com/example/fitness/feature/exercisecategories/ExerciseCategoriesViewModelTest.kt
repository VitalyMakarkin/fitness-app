package com.example.fitness.feature.exercisecategories

import com.example.fitness.core.testing.data.ExerciseCategoriesData
import com.example.fitness.core.testing.utils.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

class ExerciseCategoriesViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ExerciseCategoriesInteractor

    private lateinit var viewModel: ExerciseCategoriesViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ExerciseCategoriesViewModel(
            interactor = interactor,
        )
    }

    @Test
    fun testUiStateLoading() = runTest {
        assertEquals(
            ExerciseCategoriesUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun testUiStateSuccess() = runTest {
        val exerciseCategories = ExerciseCategoriesData
//        val uiData = exerciseCategories.map { it.mapToExerciseCategoryUI() }
//        val expectedUiState = ExerciseCategoriesUiState.Success(uiData)

        `when`(interactor.observeExerciseCategories())
            .thenReturn(flowOf(exerciseCategories))

//        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        verify(interactor).observeExerciseCategories()

//        assertEquals(
//            expectedUiState,
//            viewModel.uiState.value,
//        )

//        job.cancel()
    }

    @Test
    fun testUpdateRemoteExerciseCategories() = runTest {
        viewModel.updateRemoteExerciseCategories()

        verify(interactor).updateRemoteExerciseCategories()
    }

    @Test
    fun testDeleteExerciseCategory() = runTest {
        viewModel.deleteExerciseCategory(0)

        verify(interactor).deleteExerciseCategory(0)
    }
}
