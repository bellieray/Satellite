package com.ebelli.dashboard

import com.ebelli.core.common.base.BaseViewModel
import com.ebelli.core.common.base.IEffect
import com.ebelli.core.common.base.IState
import com.ebelli.core.data.model.Satellite
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SatellitesViewModel() : BaseViewModel<SatellitesState, SatellitesEffect>() {
    override fun setInitialState(): SatellitesState = SatellitesState.Loading
}

sealed interface SatellitesState : IState {
    object Loading : SatellitesState
    data class Success(val satellites: List<Satellite>) : SatellitesState
    object Error : SatellitesState
}

sealed interface SatellitesEffect : IEffect {

}