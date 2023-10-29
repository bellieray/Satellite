package com.ebelli.core.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebelli.core.common.JsonName
import com.ebelli.core.model.model.Position
import com.ebelli.core.data.model.SatelliteDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "satellite_detail")
@Serializable
data class SatelliteDetailDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val satelliteName: String?,
    @SerialName(JsonName.COST_PER_LAUNCH)
    val costPerLaunch: Long?,
    @SerialName(JsonName.FIRST_FLIGHT)
    val firstFlight: String?,
    val height: Int?,
    val mass: Long?,
    val position: Position?
) {
    constructor(satelliteDetail: SatelliteDetail?, satelliteName: String? = null, position: Position? = null) : this(
        satelliteDetail?.id,
        satelliteName,
        satelliteDetail?.costPerLaunch,
        satelliteDetail?.firstFlight,
        satelliteDetail?.height,
        satelliteDetail?.mass,
        position
    )
}