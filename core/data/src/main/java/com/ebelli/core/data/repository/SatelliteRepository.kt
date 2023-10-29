package com.ebelli.core.data.repository

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.Position
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    suspend fun getSatellites(): Flow<Result<List<Satellite>?>>
    suspend fun searchSatellites(query: String): Flow<Result<List<Satellite>?>>
    suspend fun getSatelliteDetail(id: Int): Flow<Result<Any>?>
    suspend fun getSatellitePosition(id: Int): Flow<Result<Position?>>
}