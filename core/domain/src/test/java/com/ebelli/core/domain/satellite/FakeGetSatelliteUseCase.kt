package com.ebelli.core.domain.satellite

import com.ebelli.core.common.Result
import com.ebelli.core.domain.satelliteList
import com.ebelli.core.domain.usecase.satellite.GetSatellitesUseCase
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetSatelliteUseCase : GetSatellitesUseCase {
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }
    override suspend fun invoke(): Flow<Result<List<Satellite>?>> = flow{
        emit(Result.Loading)

        if (showError) {
            emit(Result.Error(IOException()))
        } else {
            emit(Result.Success(satelliteList))
        }
    }
}