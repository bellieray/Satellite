package com.ebelli.core.data.datasource.local

import com.ebelli.core.model.model.SatelliteDetailDto

interface SatelliteLocalDataSource {
    suspend fun getSatelliteDetails(): List<SatelliteDetailDto>?
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto)
}