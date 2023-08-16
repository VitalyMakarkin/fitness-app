package com.example.fitness.feature.activity

sealed interface ActivityUiState {
    data class Success(val activitiesCount: Int) : ActivityUiState
    object Loading : ActivityUiState
}