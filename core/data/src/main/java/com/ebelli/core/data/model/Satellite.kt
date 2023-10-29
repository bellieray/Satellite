package com.ebelli.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Satellite(
    val id: Int?,
    val active: Boolean?,
    val name: String?
)