package com.ebelli.core.domain.di

import com.ebelli.core.data.repository.SatelliteRepository
import com.ebelli.core.domain.usecase.detail.GetSatelliteDetailUseCase
import com.ebelli.core.domain.usecase.detail.GetSatelliteDetailUseCaseImpl
import com.ebelli.core.domain.usecase.satellite.GetSatelliteUseCaseImpl
import com.ebelli.core.domain.usecase.satellite.GetSatellitesUseCase
import com.ebelli.core.domain.usecase.search.SearchSatellitesUseCase
import com.ebelli.core.domain.usecase.search.SearchSatellitesUseCaseImpl
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

    @Provides
    @Singleton
    fun provideSearchSatellitesUseCase(satelliteRepository: SatelliteRepository): SearchSatellitesUseCase =
        SearchSatellitesUseCaseImpl(satelliteRepository)

    @Provides
    @Singleton
    fun provideGetDetailSatellitesUseCase(satelliteRepository: SatelliteRepository): GetSatelliteDetailUseCase =
        GetSatelliteDetailUseCaseImpl(satelliteRepository)
}