package com.ebelli.dashboard

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ebelli.core.common.base.BaseFragment
import com.ebelli.core.common.decoration.BaseVerticalDividerItemDecoration
import com.ebelli.core.common.textChanges
import com.ebelli.dashboard.databinding.FragmentSatellitesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val MIN_TXT_LENGTH_FOR_SUGGESTION = 2
private const val SEARCH_QUERY_TIME_MILLIS = 500L
@OptIn(FlowPreview::class)
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
                            state.satellites.takeIf { it.isNullOrEmpty().not() }
                                ?.let { satellitesAdapter.submitList(state.satellites) }
                            binding.isEmpty = state.satellites.isNullOrEmpty()
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


    override fun initViews() {
        with(binding) {
            rvSatellite.adapter = satellitesAdapter
            rvSatellite.itemAnimator = null
            rvSatellite.addItemDecoration(
                BaseVerticalDividerItemDecoration(
                    requireContext(),
                    paddingOutResId = R.dimen.dimen_30,
                    paddingInResId = R.dimen.dimen_10,
                )
            )

            etSatellitSearch.setOnEditorActionListener { _, i, keyEvent ->
                if ((i == EditorInfo.IME_ACTION_SEARCH || (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER))
                ) {
                    handleSearchAction(etSatellitSearch.text)
                }
                true
            }
            etSatellitSearch.textChanges()
                .debounce(
                    SEARCH_QUERY_TIME_MILLIS
                )
                .onEach { textChange ->
                    handleSearchAction(textChange)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun handleSearchAction(text: CharSequence?) {
        val trimmedText = text?.toString()?.trim() ?: ""
        when {
            trimmedText.length >= MIN_TXT_LENGTH_FOR_SUGGESTION -> {
                viewModel.makeSearch(trimmedText)
            }

            trimmedText.isBlank() -> {
                viewModel.fetchSatellites()
            }

            else -> {
                viewModel.clearSatellites()
            }
        }
    }
}