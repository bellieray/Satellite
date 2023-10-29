package com.ebelli.core.data.datasource.json

import com.ebelli.core.model.model.Satellite
import com.ebelli.core.data.model.SatelliteDetail
import com.ebelli.core.data.model.SatellitePosition

interface SatelliteJsonDataSource {
    suspend fun getSatellites(): List<Satellite>?
    suspend fun searchSatellites(query: String) : List<Satellite>?
    suspend fun getSatelliteDetail(id: Int) : SatelliteDetail?
    suspend fun getSatellitePositions(id: Int): SatellitePosition?
}