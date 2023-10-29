package com.ebelli.core.domain.usecase.search

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.flow.Flow

interface SearchSatellitesUseCase {
    suspend operator fun invoke(query: String): Flow<Result<List<Satellite>?>>
}