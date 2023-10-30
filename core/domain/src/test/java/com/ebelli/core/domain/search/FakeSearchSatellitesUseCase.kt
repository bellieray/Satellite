package com.ebelli.core.domain.search

import com.ebelli.core.common.Result
import com.ebelli.core.domain.searchedSatelliteList
import com.ebelli.core.domain.usecase.search.SearchSatellitesUseCase
import com.ebelli.core.model.model.Satellite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeSearchSatellitesUseCase : SearchSatellitesUseCase {
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override suspend fun invoke(query: String): Flow<Result<List<Satellite>?>> = flow {
        emit(Result.Loading)

        if (showError) {
            emit(Result.Error(IOException()))
        } else {
            emit(Result.Success(searchedSatelliteList))
        }
    }
}