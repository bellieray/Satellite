package com.ebelli.dashboard

import androidx.lifecycle.viewModelScope
import com.ebelli.core.common.Result
import com.ebelli.core.common.base.BaseViewModel
import com.ebelli.core.common.base.IEffect
import com.ebelli.core.common.base.IState
import com.ebelli.core.data.model.Satellite
import com.ebelli.core.domain.usecase.satellite.GetSatellitesUseCase
import com.ebelli.core.domain.usecase.search.SearchSatellitesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val getSatellitesUseCase: GetSatellitesUseCase,
    private val searchSatellitesUseCase: SearchSatellitesUseCase
) :
    BaseViewModel<SatellitesState, IEffect>() {
    override fun setInitialState(): SatellitesState = SatellitesState.Loading
    init {
        fetchSatellites()
    }

    fun fetchSatellites() {
        viewModelScope.launch {
            getSatellitesUseCase.invoke().collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState(SatellitesState.Success(result.data))
                    }

                    is Result.Loading -> {
                        setState(SatellitesState.Loading)
                    }

                    is Result.Error -> {
                        setState(SatellitesState.Error(result.error))
                    }
                }
            }
        }
    }

    fun makeSearch(query: String) {
        viewModelScope.launch {
            searchSatellitesUseCase.invoke(query).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState(SatellitesState.Success(result.data))
                    }

                    is Result.Error -> {
                        setState(SatellitesState.Error(result.error))
                    }

                    is Result.Loading -> {
                        setState(SatellitesState.Loading)
                    }
                }
            }
        }
    }

    fun clearSatellites() {
        setState(SatellitesState.Success(null))
    }
}

sealed interface SatellitesState : IState {
    object Loading : SatellitesState
    data class Success(val satellites: List<Satellite>?) : SatellitesState
    data class Error(val error: Exception?) : SatellitesState
}