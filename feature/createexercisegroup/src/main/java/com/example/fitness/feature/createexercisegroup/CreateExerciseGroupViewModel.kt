package com.example.fitness.feature.createexercisegroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseGroupItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val GROUP_NAME = "createExerciseGroupName"
private const val GROUP_EXERCISES = "createExercises"

@HiltViewModel
class CreateExerciseGroupViewModel @Inject constructor(
    private val interactor: CreateExerciseGroupInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val categoryName = savedStateHandle.getStateFlow(GROUP_NAME, "")

    var shouldNavigateBack by mutableStateOf(false)

    val uiState: StateFlow<CreateExerciseGroupUiState> =
        createExerciseGroupUiState()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CreateExerciseGroupUiState.Loading
            )

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun createExerciseGroupUiState(): Flow<CreateExerciseGroupUiState> {
        return savedStateHandle.getStateFlow(GROUP_EXERCISES, emptyList<ExerciseGroupItem>())
            .flatMapLatest { list ->
                flowOf(CreateExerciseGroupUiState.Success(list))
            }
    }

    fun onCategoryNameChanged(text: String) {
        savedStateHandle[GROUP_NAME] = text
    }

    fun addExercise(exerciseGroupItem: ExerciseGroupItem) {
        val newList = mutableListOf<ExerciseGroupItem>()
            .apply {
                val currentExercises =
                    savedStateHandle.get<List<ExerciseGroupItem>>(GROUP_EXERCISES) ?: emptyList()
                addAll(currentExercises)
                add(exerciseGroupItem)
            }
        savedStateHandle[GROUP_EXERCISES] = newList
    }

    fun createExerciseCategory() {
        val categoryName = savedStateHandle.get<String>(GROUP_NAME) ?: ""
        val exercises = savedStateHandle.get<List<ExerciseGroupItem>>(GROUP_EXERCISES)
                ?: emptyList()

        viewModelScope.launch {
            interactor.createExerciseGroup(
                name = categoryName,
                exercises = exercises
            )

            shouldNavigateBack = true
        }
    }
}