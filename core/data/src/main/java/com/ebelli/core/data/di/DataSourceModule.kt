package com.ebelli.core.data.di

import com.ebelli.core.asset.AssetHelper
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSatelliteJsonDataSource(
        assetHelper: AssetHelper,
        ioDispatcher: CoroutineDispatcher,
        json: Json
    ): SatelliteJsonDataSource = SatelliteJsonDataSourceImpl(assetHelper, ioDispatcher, json)
}