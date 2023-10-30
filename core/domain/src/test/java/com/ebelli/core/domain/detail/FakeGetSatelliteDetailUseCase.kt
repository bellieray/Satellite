package com.ebelli.core.domain.detail

import com.ebelli.core.common.Result
import com.ebelli.core.domain.satelliteDetailDto
import com.ebelli.core.domain.usecase.detail.GetSatelliteDetailUseCase
import com.ebelli.core.model.model.SatelliteDetailDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetSatelliteDetailUseCase : GetSatelliteDetailUseCase {
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override suspend fun invoke(id: Int): Flow<Result<SatelliteDetailDto?>> = flow {
        emit(Result.Loading)

        if (showError) {
            emit(Result.Error(IOException()))
        } else {
            emit(Result.Success(satelliteDetailDto))
        }
    }
}