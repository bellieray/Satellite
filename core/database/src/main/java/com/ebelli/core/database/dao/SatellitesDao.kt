package com.ebelli.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ebelli.core.model.model.SatelliteDetailDto

@Dao
interface SatellitesDao {
    @Query("SELECT * FROM satellite_detail")
    suspend fun getSatelliteDetails(): List<SatelliteDetailDto>?

    @Insert
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto)
}