package com.ebelli.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ebelli.core.common.Result
import com.ebelli.core.common.base.BaseViewModel
import com.ebelli.core.common.base.IState
import com.ebelli.core.model.model.SatelliteDetailDto
import com.ebelli.core.domain.usecase.detail.GetSatelliteDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<SatelliteDetailState>() {
    override fun setInitialState(): SatelliteDetailState = SatelliteDetailState.Loading

    init {
        savedStateHandle.get<Int>("satelliteId")?.takeIf { it > 0 }?.let { id ->
            getSatelliteDetail(id)
        }
    }

     private fun getSatelliteDetail(id: Int) {
        viewModelScope.launch {
            getSatelliteDetailUseCase.invoke(id).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState(SatelliteDetailState.Success(result.data))
                    }

                    is Result.Loading -> {
                        setState(SatelliteDetailState.Loading)
                    }

                    is Result.Error -> {
                        setState(SatelliteDetailState.Error(result.error))
                    }
                }
            }
        }
    }
}

sealed interface SatelliteDetailState : IState {
    object Loading : SatelliteDetailState
    data class Success(val satelliteDetail: SatelliteDetailDto?) : SatelliteDetailState
    data class Error(val error: Exception?) : SatelliteDetailState
}