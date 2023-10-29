package com.ebelli.core.database.di

import android.app.Application
import androidx.room.Room
import com.ebelli.core.database.SatellitesDatabase
import com.ebelli.core.database.converter.SatelliteRoomTypeConverter
import com.ebelli.core.database.dao.SatellitesDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        typeConverter: SatelliteRoomTypeConverter
    ): SatellitesDatabase =
        Room.databaseBuilder(app, SatellitesDatabase::class.java, "satellite_db")
            .addTypeConverter(typeConverter)
            .build()

    @Provides
    @Singleton
    fun provideSatelliteDao(satellitesDatabase: SatellitesDatabase): SatellitesDao =
        satellitesDatabase.getSatelliteDao()

    @Provides
    @Singleton
    fun provideConverter(gson: Gson): SatelliteRoomTypeConverter = SatelliteRoomTypeConverter(gson)

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().create()
}