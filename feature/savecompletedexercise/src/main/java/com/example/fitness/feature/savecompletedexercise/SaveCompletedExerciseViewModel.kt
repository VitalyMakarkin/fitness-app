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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
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
    val exerciseSets = savedStateHandle.getStateFlow(EXERCISE_SETS, "")
    val exerciseReps = savedStateHandle.getStateFlow(EXERCISE_REPS, "")
    val exerciseDuration = savedStateHandle.getStateFlow(EXERCISE_DURATION, "")

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
            .zip(
                savedStateHandle.getStateFlow(EXERCISE_COMPLETED_AT, System.currentTimeMillis())
            ) { categoryId, completedAt -> Pair(categoryId, completedAt) }
            .flatMapLatest { (categoryId, completedAt) ->
                if (categoryId != -1L) {
                    interactor.observeExerciseCategory(categoryId)
                        .map { category ->
                            SaveCompletedExerciseUiState.Success(
                                selectedExerciseCategory = category,
                                selectedCompletedAt = completedAt
                            )
                        }
                } else {
                    flowOf(
                        SaveCompletedExerciseUiState.Success(
                            selectedExerciseCategory = null,
                            selectedCompletedAt = completedAt
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
        savedStateHandle[EXERCISE_SETS] = text
    }

    fun onExerciseRepsChanged(text: String) {
        savedStateHandle[EXERCISE_REPS] = text
    }

    fun onExerciseDurationChanged(text: String) {
        savedStateHandle[EXERCISE_DURATION] = text
    }

    fun onExerciseScoreChanged(value: Int) {
        savedStateHandle[EXERCISE_SCORE] = value
    }

    fun saveExercise() {
        viewModelScope.launch {

            val exerciseName = savedStateHandle.get<String>(EXERCISE_NAME) ?: ""
            val exerciseCategoryId = savedStateHandle.get<Long>(EXERCISE_CATEGORY_ID) ?: 0L
            val exerciseCompletedAt =
                savedStateHandle.get<Long>(EXERCISE_COMPLETED_AT) ?: System.currentTimeMillis()
            val exerciseSets =
                savedStateHandle.get<String>(EXERCISE_SETS)?.let { it.ifEmpty { null } }?.toInt()
            val exerciseReps =
                savedStateHandle.get<String>(EXERCISE_REPS)?.let { it.ifEmpty { null } }?.toInt()
            val exerciseDuration =
                savedStateHandle.get<String>(EXERCISE_DURATION)?.let { it.ifEmpty { null } }
                    ?.toLong()?.times(60 * 1000)
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