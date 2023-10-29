package com.ebelli.core.data.di

import com.ebelli.core.asset.AssetHelper
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSourceImpl
import com.ebelli.core.data.datasource.local.SatelliteLocalDataSource
import com.ebelli.core.data.datasource.local.SatelliteLocalDataSourceImpl
import com.ebelli.core.database.dao.SatellitesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSatelliteJsonDataSource(
        assetHelper: AssetHelper,
        ioDispatcher: CoroutineDispatcher
    ): SatelliteJsonDataSource = SatelliteJsonDataSourceImpl(assetHelper, ioDispatcher)

    @Provides
    @Singleton
    fun provideSatelliteLocalDataSource(
        ioDispatcher: CoroutineDispatcher,
        satellitesDao: SatellitesDao
    ): SatelliteLocalDataSource = SatelliteLocalDataSourceImpl(satellitesDao, ioDispatcher)
}