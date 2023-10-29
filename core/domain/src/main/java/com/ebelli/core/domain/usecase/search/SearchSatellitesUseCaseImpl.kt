package com.ebelli.core.domain.usecase.search

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.Satellite
import com.ebelli.core.data.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSatellitesUseCaseImpl @Inject constructor(private val satelliteRepository: SatelliteRepository) :
    SearchSatellitesUseCase {
    override suspend fun invoke(query: String): Flow<Result<List<Satellite>?>> =
        satelliteRepository.searchSatellites(query)
}