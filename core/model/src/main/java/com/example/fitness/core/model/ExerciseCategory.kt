package com.example.fitness.core.model

data class ExerciseCategory(
    val id: Int,
    val name: String,
    val description: String?,
    val containsSets: RequiredState,
    val containsReps: RequiredState,
    val containsDuration: RequiredState
) {

    enum class RequiredState {
        NONE,
        OPTIONAL,
        REQUIRED
    }
}
