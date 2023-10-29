package com.ebelli.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebelli.core.model.model.Position
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "satellite_positions")
data class SatellitePosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val positions: List<Position>?
)