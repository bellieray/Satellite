package com.ebelli.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val posX: Double?,
    val posY: Double?
)