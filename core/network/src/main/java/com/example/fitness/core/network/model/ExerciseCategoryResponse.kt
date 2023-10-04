package com.example.fitness.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseCategoryResponse(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)
