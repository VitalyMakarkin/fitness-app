package com.example.fitness.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseCategoryPageResponse(

    @SerialName("count")
    val count: Int,

    @SerialName("results")
    val results: List<ExerciseCategoryResponse>
)
