package com.ebelli.core.data.datasource.json

import com.ebelli.core.data.model.Satellite

interface SatelliteJsonDataSource {
    suspend fun getSatellites(): List<Satellite>?
}