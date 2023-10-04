package com.example.fitness.feature.activity

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

class ActivityViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Mock private lateinit var interactor: ActivityInteractor

    private lateinit var viewModel: ActivityViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = ActivityViewModel(
            interactor = interactor,
        )
    }

    @Test
    fun testUiStateLoading() = runTest {
        assertEquals(
            ActivityUiState.Loading,
            viewModel.uiState.value
        )
    }

    @Test
    fun testUiStateSuccess() = runTest {
        `when`(interactor.observeExercisesCount())
            .thenReturn(flowOf(1))

        verify(interactor).observeExercisesCount()
    }
}
