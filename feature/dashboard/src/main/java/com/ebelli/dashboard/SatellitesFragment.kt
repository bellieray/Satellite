package com.ebelli.dashboard

import com.ebelli.core.common.base.BaseFragment
import com.ebelli.core.common.decoration.BaseVerticalDividerItemDecoration
import com.ebelli.dashboard.databinding.FragmentSatellitesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatellitesFragment :
    BaseFragment<FragmentSatellitesBinding, SatellitesViewModel>(R.layout.fragment_satellites) {
    override fun getViewModelClass(): Class<SatellitesViewModel> = SatellitesViewModel::class.java
    private val satellitesAdapter: SatellitesAdapter by lazy { SatellitesAdapter() }
    override fun initObserver() {

    }

    override fun initViews() = with(binding) {
        rvSatellite.adapter = satellitesAdapter
        rvSatellite.addItemDecoration(
            BaseVerticalDividerItemDecoration(
                requireContext(),
                paddingInResId = R.dimen.dimen_10,
                paddingOutResId = R.dimen.dimen_10,
                dividerResId = R.drawable.divider_orange_with_1dp
            )
        )
    }
}