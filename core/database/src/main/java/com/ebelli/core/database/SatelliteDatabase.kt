package com.ebelli.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ebelli.core.model.model.SatelliteDetailDto
import com.ebelli.core.database.converter.SatelliteRoomTypeConverter
import com.ebelli.core.database.dao.SatellitesDao

@Database(
    entities = [SatelliteDetailDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SatelliteRoomTypeConverter::class)
abstract class SatellitesDatabase : RoomDatabase() {
    abstract fun getSatelliteDao(): SatellitesDao
}