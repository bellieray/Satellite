package com.ebelli.core.data.datasource.json

import com.ebelli.core.asset.AssetHelper
import com.ebelli.core.common.JsonFile
import com.ebelli.core.data.model.Satellite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class SatelliteJsonDataSourceImpl @Inject constructor(
    private val assetHelper: AssetHelper,
    private val ioDispatcher: CoroutineDispatcher,
    private val json: Json
) :
    SatelliteJsonDataSource {
    override suspend fun getSatellites(): List<Satellite>? = withContext(ioDispatcher) {
        assetHelper.open(JsonFile.SATELLITE_LIST_JSON).use(json::decodeFromStream)
    }
}