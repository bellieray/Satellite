package com.ebelli.core.domain.di

import com.ebelli.core.data.repository.SatelliteRepository
import com.ebelli.core.domain.usecase.satellite.GetSatelliteUseCaseImpl
import com.ebelli.core.domain.usecase.satellite.GetSatellitesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetSatellitesUseCase(satelliteRepository: SatelliteRepository): GetSatellitesUseCase =
        GetSatelliteUseCaseImpl(satelliteRepository)
}