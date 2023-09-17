package com.example.fitness.feature.savecompletedexercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val EXERCISE_NAME = "saveCompletedExerciseName"
private const val EXERCISE_CATEGORY_ID = "saveCompletedExerciseCategoryId"
private const val EXERCISE_COMPLETED_AT = "saveCompletedExerciseCompletedAt"
private const val EXERCISE_SETS = "saveCompletedExerciseSets"
private const val EXERCISE_REPS = "saveCompletedExerciseReps"
private const val EXERCISE_DURATION = "saveCompletedExerciseDuration"
private const val EXERCISE_SCORE = "saveCompletedExerciseScore"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SaveCompletedExerciseViewModel @Inject constructor(
    private val interactor: SaveCompletedExerciseInteractor,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val exerciseName = savedStateHandle.getStateFlow(EXERCISE_NAME, "")
    val exerciseSets = savedStateHandle.getStateFlow(EXERCISE_SETS, 0)
    val exerciseReps = savedStateHandle.getStateFlow(EXERCISE_REPS, 0)
    val exerciseDuration = savedStateHandle.getStateFlow(EXERCISE_DURATION, 0L)
    val exerciseScore = savedStateHandle.getStateFlow(EXERCISE_SCORE, 0)

    val uiState: StateFlow<SaveCompletedExerciseUiState> =
        saveCompletedExerciseUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SaveCompletedExerciseUiState.Loading
        )

    var shouldNavigateBack by mutableStateOf(false)

    private fun saveCompletedExerciseUiState(interactor: SaveCompletedExerciseInteractor): Flow<SaveCompletedExerciseUiState> {
        return savedStateHandle.getStateFlow(EXERCISE_CATEGORY_ID, -1L)
            .combine(
                savedStateHandle.getStateFlow(EXERCISE_COMPLETED_AT, -1L)
            ) { categoryId, completedAt ->
                Pair(categoryId, completedAt)
            }
            .flatMapLatest { (categoryId, completedAt) ->
                val newCompletedAt = when (completedAt) {
                    -1L -> System.currentTimeMillis()
                    else -> completedAt
                }
                if (categoryId != -1L) {
                    interactor.observeExerciseCategory(categoryId)
                        .map { category ->
                            SaveCompletedExerciseUiState.Success(
                                selectedExerciseCategory = category,
                                selectedCompletedAt = newCompletedAt
                            )
                        }
                } else {
                    flowOf(
                        SaveCompletedExerciseUiState.Success(
                            selectedExerciseCategory = null,
                            selectedCompletedAt = newCompletedAt
                        )
                    )
                }
            }
    }

    fun changeExerciseCategory(category: ExerciseCategory) {
        savedStateHandle[EXERCISE_CATEGORY_ID] = category.id
    }

    fun onExerciseNameChanged(text: String) {
        savedStateHandle[EXERCISE_NAME] = text
    }

    fun onExerciseCompletedAtChanged(value: Long) {
        savedStateHandle[EXERCISE_COMPLETED_AT] = value
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

    fun onExerciseScoreChanged(text: String) {
        savedStateHandle[EXERCISE_SCORE] = text.toInt()
    }

    fun saveExercise() {
        viewModelScope.launch {

            val exerciseName = savedStateHandle.get<String>(EXERCISE_NAME) ?: ""
            val exerciseCategoryId = savedStateHandle.get<Long>(EXERCISE_CATEGORY_ID) ?: 0L
            val exerciseCompletedAt = savedStateHandle.get<Long>(EXERCISE_COMPLETED_AT) ?: 0L
            val exerciseSets = savedStateHandle.get<Int>(EXERCISE_SETS) ?: 0
            val exerciseReps = savedStateHandle.get<Int>(EXERCISE_REPS) ?: 0
            val exerciseDuration = savedStateHandle.get<Long>(EXERCISE_DURATION) ?: 0L
            val exerciseScore = savedStateHandle.get<Int>(EXERCISE_SCORE) ?: 0

            val currentTime = System.currentTimeMillis()
            interactor.saveExercise(
                name = exerciseName,
                exerciseCategoryId = exerciseCategoryId,
                createdAt = currentTime,
                completedAt = exerciseCompletedAt,
                sets = exerciseSets,
                reps = exerciseReps,
                duration = exerciseDuration,
                score = exerciseScore
            )

            shouldNavigateBack = true
        }
    }
}