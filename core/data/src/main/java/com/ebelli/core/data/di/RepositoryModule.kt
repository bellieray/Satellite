package com.ebelli.core.data.di

import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.datasource.local.SatelliteLocalDataSource
import com.ebelli.core.data.repository.SatelliteRepository
import com.ebelli.core.data.repository.SatelliteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSatelliteRepository(
        jsonDataSource: SatelliteJsonDataSource,
        satelliteLocalDataSource: SatelliteLocalDataSource
    ): SatelliteRepository = SatelliteRepositoryImpl(jsonDataSource, satelliteLocalDataSource)
}