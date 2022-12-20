package com.example.weathertoday.core.services.di

import com.example.weathertoday.data.repository.WeatherRepositoryImplementation
import com.example.weathertoday.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImplementation: WeatherRepositoryImplementation
    ): WeatherRepository
}