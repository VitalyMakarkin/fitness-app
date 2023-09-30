package com.example.fitness.feature.activity

sealed interface ActivityUiState {

    data class Success(
        val activitiesCount: Int,
        val averageScore: Double,
    ) : ActivityUiState

    object Loading : ActivityUiState
}