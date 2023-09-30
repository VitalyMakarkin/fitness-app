package com.example.fitness.feature.createexercisecategory

import androidx.lifecycle.SavedStateHandle
import com.example.fitness.core.testing.utils.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

class CreateExerciseCategoryViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: CreateExerciseCategoryInteractor

    @Mock private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: CreateExerciseCategoryViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = CreateExerciseCategoryViewModel(
            interactor = interactor,
            savedStateHandle = savedStateHandle,
        )
    }

    @Test
    fun testCreateExerciseCategory() = runTest {
        `when`(savedStateHandle.get<String>("createExerciseCategoryName"))
            .thenReturn("Name")
        `when`(savedStateHandle.get<String>("createExerciseCategoryDescription"))
            .thenReturn("Description")

        viewModel.createExerciseCategory()

        verify(interactor).createExerciseCategory("Name", "Description")
        assertEquals(true, viewModel.shouldNavigateBack)
    }
}