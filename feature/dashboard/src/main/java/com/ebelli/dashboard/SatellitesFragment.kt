package com.ebelli.dashboard

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ebelli.core.common.base.BaseFragment
import com.ebelli.core.common.decoration.BaseVerticalDividerItemDecoration
import com.ebelli.dashboard.databinding.FragmentSatellitesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatellitesFragment :
    BaseFragment<FragmentSatellitesBinding, SatellitesViewModel>(R.layout.fragment_satellites) {
    override fun getViewModelClass(): Class<SatellitesViewModel> = SatellitesViewModel::class.java
    private val satellitesAdapter: SatellitesAdapter by lazy { SatellitesAdapter() }
    override fun initObserver() {
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is SatellitesState.Success -> {
                            satellitesAdapter.submitList(state.satellites)
                            hideProgressBar()
                        }

                        is SatellitesState.Error -> {
                            notify(state.error)
                            hideProgressBar()
                        }

                        is SatellitesState.Loading -> {
                            showProgressBar()
                            delay(1500)
                        }
                    }
                }
        }
    }

    private fun hideProgressBar() {
        binding.isLoading = false
    }

    private fun showProgressBar() {
        binding.isLoading = true
    }

    override fun initViews() = with(binding) {
        rvSatellite.adapter = satellitesAdapter
        rvSatellite.itemAnimator = null
        rvSatellite.addItemDecoration(
            BaseVerticalDividerItemDecoration(
                requireContext(),
                paddingOutResId = R.dimen.dimen_30,
                paddingInResId = R.dimen.dimen_10,
            )
        )
    }
}