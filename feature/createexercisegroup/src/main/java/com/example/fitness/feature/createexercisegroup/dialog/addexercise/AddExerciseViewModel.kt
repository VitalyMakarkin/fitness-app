package com.example.fitness.feature.createexercisegroup.dialog.addexercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroupItem
import com.example.fitness.feature.createexercisegroup.CreateExerciseGroupInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val EXERCISE_NAME = "addExerciseName"
private const val EXERCISE_CATEGORY_ID = "addExerciseCategoryId"
private const val EXERCISE_SETS = "addExerciseSets"
private const val EXERCISE_REPS = "addExerciseReps"
private const val EXERCISE_DURATION = "addExerciseDuration"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val interactor: CreateExerciseGroupInteractor,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val exerciseName = savedStateHandle.getStateFlow(EXERCISE_NAME, "")
    val exerciseSets = savedStateHandle.getStateFlow(EXERCISE_SETS, 0)
    val exerciseReps = savedStateHandle.getStateFlow(EXERCISE_REPS, 0)
    val exerciseDuration = savedStateHandle.getStateFlow(EXERCISE_DURATION, 0L)

    val addExerciseUiState: StateFlow<AddExerciseUiState> =
        addExerciseUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddExerciseUiState.Loading
        )

    var shouldNavigateBack by mutableStateOf(false)

    private fun addExerciseUiState(interactor: CreateExerciseGroupInteractor): Flow<AddExerciseUiState> {
        return savedStateHandle.getStateFlow(EXERCISE_CATEGORY_ID, -1)
            .flatMapLatest { categoryId ->
                if (categoryId != -1) {
                    interactor.observeExerciseCategory(categoryId)
                        .map { category -> AddExerciseUiState.Success(category) }
                } else {
                    flowOf(AddExerciseUiState.Success(null))
                }
            }
    }

    fun changeExerciseCategory(category: ExerciseCategory) {
        savedStateHandle[EXERCISE_CATEGORY_ID] = category.id
    }

    fun onExerciseNameChanged(text: String) {
        savedStateHandle[EXERCISE_NAME] = text
    }

    fun onExerciseSetsChanged(text: String) {
        savedStateHandle[EXERCISE_SETS] = text.toInt()
    }

    fun onExerciseRepsChanged(text: String) {
        savedStateHandle[EXERCISE_REPS] = text.toInt()
    }

    fun onExerciseDurationChanged(text: String) {
        savedStateHandle[EXERCISE_DURATION] = text.toLong()
    }

    fun getConfirmedNewExercise(): ExerciseGroupItem {
        val exerciseName = savedStateHandle.get<String>(EXERCISE_NAME) ?: ""
        val exerciseCategoryId = savedStateHandle.get<Int>(EXERCISE_CATEGORY_ID) ?: -1
        val exerciseSets = savedStateHandle.get<Int>(EXERCISE_SETS)
        val exerciseReps = savedStateHandle.get<Int>(EXERCISE_REPS)
        val exerciseDuration = savedStateHandle.get<Long>(EXERCISE_DURATION)

        return ExerciseGroupItem(
            0,
            exerciseName,
            exerciseCategoryId,
            exerciseSets,
            exerciseReps,
            exerciseDuration
        )
    }
}