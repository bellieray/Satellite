package com.ebelli.core.data.datasource.local

import com.ebelli.core.model.model.SatelliteDetailDto
import com.ebelli.core.database.dao.SatellitesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteLocalDataSourceImpl @Inject constructor(private val satellitesDao: SatellitesDao, private val ioDispatcher: CoroutineDispatcher) : SatelliteLocalDataSource {
    override suspend fun getSatelliteDetails(): List<SatelliteDetailDto>? =
        withContext(ioDispatcher) { satellitesDao.getSatelliteDetails() }

    override suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto) =
        withContext(ioDispatcher) {
            satellitesDao.insertSatelliteDetail(satelliteDetail)
        }
}