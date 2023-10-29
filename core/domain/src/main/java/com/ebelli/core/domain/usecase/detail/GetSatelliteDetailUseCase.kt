package com.ebelli.core.domain.usecase.detail

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.SatelliteDetailDto
import kotlinx.coroutines.flow.Flow

interface GetSatelliteDetailUseCase {
    suspend operator fun invoke(id: Int) : Flow<Result<SatelliteDetailDto?>>
}