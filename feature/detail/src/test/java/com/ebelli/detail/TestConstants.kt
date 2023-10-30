package com.ebelli.detail

import com.ebelli.core.model.model.Position
import com.ebelli.core.model.model.SatelliteDetailDto
import org.jetbrains.annotations.VisibleForTesting

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
val satelliteTestId = 1