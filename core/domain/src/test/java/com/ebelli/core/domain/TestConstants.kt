package com.ebelli.core.domain

import com.ebelli.core.model.model.Position
import com.ebelli.core.model.model.Satellite
import com.ebelli.core.model.model.SatelliteDetailDto
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
val satelliteList = listOf(
    Satellite(
        id = 1,
        active = false,
        name = "Starship-1"
    ),
    Satellite(
        id = 2,
        active = false,
        name = "Dragon-1"
    ),
    Satellite(
        id = 3,
        active = false,
        name = "Starship-3"
    )
)

@VisibleForTesting
val satelliteDetailDto = SatelliteDetailDto(
    id = 1,
    satelliteName = "Starship-1",
    costPerLaunch = 7200000,
    firstFlight = "2021-12-01",
    height = 118,
    mass = 1167000,
    position = Position(
        posX = 0.864328541,
        posY = 0.646450811
    )
)

@VisibleForTesting
val searchedSatelliteList = listOf(
    Satellite(
        id = 1,
        active = false,
        name = "Starship-1"
    ),
    Satellite(
        id = 2,
        active = false,
        name = "Dragon-1"
    )
)

const val query = "sta"
const val satelliteId = 1