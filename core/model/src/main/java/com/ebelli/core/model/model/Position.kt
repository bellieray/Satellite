package com.ebelli.core.model.model

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val posX: Double?,
    val posY: Double?
)