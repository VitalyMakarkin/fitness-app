package com.example.fitness.core.network.api

import com.example.fitness.core.network.model.ExerciseCategoryPageResponse
import retrofit2.Response
import retrofit2.http.GET

interface WgerApi {

    @GET("exercisecategory")
    suspend fun getExerciseCategories(): Response<ExerciseCategoryPageResponse>
}