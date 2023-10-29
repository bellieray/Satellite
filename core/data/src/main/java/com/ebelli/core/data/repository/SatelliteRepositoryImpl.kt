package com.ebelli.core.data.repository

import com.ebelli.core.common.Result
import com.ebelli.core.common.flowCall
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.datasource.local.SatelliteLocalDataSource
import com.ebelli.core.model.model.Position
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val jsonDataSource: SatelliteJsonDataSource,
    private val satelliteLocalDataSource: SatelliteLocalDataSource
) : SatelliteRepository {
    override suspend fun getSatellites(): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.getSatellites() }
    override suspend fun searchSatellites(query: String): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.searchSatellites(query) }
    override suspend fun getSatelliteDetail(id: Int): Flow<Result<Any>?> =
        flowCall {
            satelliteLocalDataSource.getSatelliteDetails()?.find { it.id == id } ?: jsonDataSource.getSatelliteDetail(id)
        }

    override suspend fun getSatellitePosition(id: Int): Flow<Result<Position?>> = flowCall {
        jsonDataSource.getSatellitePositions(id)?.positions?.map {
            Position(
                it.posX,
                it.posY
            )
        }?.get(0)
    }
}