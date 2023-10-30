package com.ebelli.core.data.datasource.json

import com.ebelli.core.asset.AssetHelper
import com.ebelli.core.common.JsonFile
import com.ebelli.core.common.decode
import com.ebelli.core.data.model.SatelliteDetail
import com.ebelli.core.data.model.SatellitePosition
import com.ebelli.core.data.model.SatellitePositionsList
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteJsonDataSourceImpl @Inject constructor(
    private val assetHelper: AssetHelper,
    private val ioDispatcher: CoroutineDispatcher,
) :
    SatelliteJsonDataSource {
    override suspend fun getSatellites(): List<Satellite>? = withContext(ioDispatcher) {
        assetHelper.decode(JsonFile.SATELLITE_LIST_JSON)
    }

    override suspend fun searchSatellites(query: String): List<Satellite>? =
        withContext(ioDispatcher) {
            assetHelper.decode<List<Satellite>?>(JsonFile.SATELLITE_LIST_JSON)
                ?.filter { it.name?.lowercase()?.contains(query) == true }
        }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetail? = withContext(ioDispatcher) {
        assetHelper.decode<Array<SatelliteDetail>>(JsonFile.SATELLITE_DETAIL_JSON)
            ?.find { it.id == id }
    }

    override suspend fun getSatellitePositions(id: Int): SatellitePosition? =
        withContext(ioDispatcher) {
            assetHelper.decode<SatellitePositionsList>(JsonFile.SATELLITE_POSITIONS_JSON)?.list?.find { it.id == id }
        }
}