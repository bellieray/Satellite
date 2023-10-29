package com.ebelli.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ebelli.core.common.base.BaseFragment
import com.ebelli.detail.databinding.FragmentSatelliteDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment :
    BaseFragment<FragmentSatelliteDetailBinding, SatelliteDetailViewModel>(R.layout.fragment_satellite_detail) {
    override fun getViewModelClass(): Class<SatelliteDetailViewModel> =
        SatelliteDetailViewModel::class.java

    override fun initObserver() {
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is SatelliteDetailState.Success -> {
                            state.satelliteDetail?.let {
                                binding.satelliteDetail = it
                            }
                            hideProgressBar()
                        }

                        is SatelliteDetailState.Error -> {
                            hideProgressBar()
                            notify(state.error)

                        }

                        is SatelliteDetailState.Loading -> {
                            showProgressBar()
                            delay(1500)
                        }
                    }
                }
        }
    }

    override fun initViews() {
        binding.ivSatelliteDetailBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun hideProgressBar() {
        binding.isLoading = false
    }

    private fun showProgressBar() {
        binding.isLoading = true
    }
}