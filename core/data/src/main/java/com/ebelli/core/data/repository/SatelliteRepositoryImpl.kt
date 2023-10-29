package com.ebelli.core.data.repository

import com.ebelli.core.common.Result
import com.ebelli.core.common.flowCall
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.model.Satellite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(private val jsonDataSource: SatelliteJsonDataSource) : SatelliteRepository {
    override suspend fun getSatellites(): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.getSatellites() }
    override suspend fun searchSatellites(query: String): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.searchSatellites(query) }
}