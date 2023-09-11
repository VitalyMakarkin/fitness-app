package com.example.fitness.core.network.api

import retrofit2.http.GET

interface WgerApi {

    @GET
    suspend fun getExerciseCategories()
}