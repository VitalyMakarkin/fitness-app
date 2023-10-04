package com.example.fitness.core.network.di

import com.example.fitness.core.network.api.WgerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideWgerApi(retrofit: Retrofit): WgerApi {
        return retrofit.create(WgerApi::class.java)
    }
}
