package com.ebelli.dashboard

import com.ebelli.core.model.model.Satellite
import org.jetbrains.annotations.VisibleForTesting

const val query = "sta"

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