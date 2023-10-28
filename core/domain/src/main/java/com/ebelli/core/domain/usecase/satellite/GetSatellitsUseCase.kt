package com.ebelli.core.domain.usecase.satellite

import com.ebelli.core.common.Result
import com.ebelli.core.data.model.Satellite
import kotlinx.coroutines.flow.Flow

interface GetSatellitesUseCase {
    suspend operator fun invoke(): Flow<Result<List<Satellite>?>>
}