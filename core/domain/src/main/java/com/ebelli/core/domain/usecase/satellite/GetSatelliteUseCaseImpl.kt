package com.ebelli.core.domain.usecase.satellite

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.Satellite
import com.ebelli.core.data.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatelliteUseCaseImpl @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : GetSatellitesUseCase {
    override suspend fun invoke(): Flow<Result<List<Satellite>?>> =
        satelliteRepository.getSatellites()
}