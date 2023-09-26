package com.example.fitness.feature.exercisecategories

import com.example.fitness.core.model.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class ExerciseCategoriesViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ExerciseCategoriesInteractor

    private lateinit var viewModel: ExerciseCategoriesViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ExerciseCategoriesViewModel(
            interactor = interactor
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
        val exerciseCategories = testExerciseCategories
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
}

private val testExerciseCategories = listOf(
    ExerciseCategory(
        id = 1,
        name = "Category 1",
        description = "Description 1"
    ),
    ExerciseCategory(
        id = 2,
        name = "Category 2",
        description = "Description 2"
    ),
    ExerciseCategory(
        id = 3,
        name = "Category 3",
        description = "Description 3"
    ),
)