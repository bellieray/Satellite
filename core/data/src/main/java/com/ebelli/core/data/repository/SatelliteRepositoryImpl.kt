package com.ebelli.core.data.repository

import com.ebelli.core.common.Result
import com.ebelli.core.common.flowCall
import com.ebelli.core.data.datasource.json.SatelliteJsonDataSource
import com.ebelli.core.data.datasource.local.SatelliteLocalDataSource
import com.ebelli.core.model.model.Position
import com.ebelli.core.model.model.Satellite
import com.ebelli.core.model.model.SatelliteDetailDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val jsonDataSource: SatelliteJsonDataSource,
    private val satelliteLocalDataSource: SatelliteLocalDataSource
) : SatelliteRepository {
    override suspend fun getSatellites(): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.getSatellites() }
    override suspend fun searchSatellites(query: String): Flow<Result<List<Satellite>?>> = flowCall { jsonDataSource.searchSatellites(query) }
    override suspend fun getSatelliteDetail(id: Int): Flow<Result<Any>?> =
        flowCall {
            satelliteLocalDataSource.getSatelliteDetails()?.find { it.id == id }
                ?: jsonDataSource.getSatelliteDetail(id)?.also { satelliteDetail ->
                    satelliteLocalDataSource.insertSatelliteDetail(
                        SatelliteDetailDto(
                            satelliteDetail
                        )
                    )
                }
        }

    override suspend fun getSatellitePosition(id: Int): Flow<Result<Position?>> =
        flow<Result<Position>> {
            while (true) {
                val positions = jsonDataSource.getSatellitePositions(id)?.positions
                positions?.forEach { position ->
                    emit(Result.Success(position))
                    delay(3000)
                }
            }
        }.catch { throwable ->
            emit(Result.Error(throwable as? Exception))
        }
}