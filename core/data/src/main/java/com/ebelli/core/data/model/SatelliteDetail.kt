package com.ebelli.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebelli.core.common.JsonName.COST_PER_LAUNCH
import com.ebelli.core.common.JsonName.FIRST_FLIGHT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "satellite_detail")
data class SatelliteDetail(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerialName(COST_PER_LAUNCH)
    val costPerLaunch: Long?,
    @SerialName(FIRST_FLIGHT)
    val firstFlight: String?,
    val height: Int?,
    val mass: Long?
)