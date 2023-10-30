package com.ebelli.detail

import androidx.lifecycle.SavedStateHandle
import com.ebelli.core.common.Result
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
 class DetailViewModelTest {
    @Mock
    private lateinit var getFakeSatelliteDetailUseCase: FakeGetSatelliteDetailUseCase


    private lateinit var satelliteDetailViewModel: SatelliteDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        satelliteDetailViewModel = SatelliteDetailViewModel(
            getFakeSatelliteDetailUseCase,
            savedStateHandle = SavedStateHandle().apply {
                set("id",1)
            }
        )
    }

    @Test
    fun state_WhenUseCaseReturnsDownloading_isDownloading() {
        runTest {
            val resultList = listOf(Result.Loading)
            whenever(getFakeSatelliteDetailUseCase.invoke(satelliteTestId)).thenReturn(resultList.asFlow())

            val listOfEmittedResult = mutableListOf<SatelliteDetailState>(
                SatelliteDetailState.Loading
            )
            val job = launch {
                satelliteDetailViewModel.state.value
            }
            satelliteDetailViewModel.getSatelliteDetail(satelliteTestId)
            Mockito.verify(getFakeSatelliteDetailUseCase).invoke(satelliteTestId)
            assert(listOfEmittedResult.first() is SatelliteDetailState.Loading)
            job.cancel()
        }
    }


    @Test
    fun state_WhenUseCaseReturnsDownloadingAndSuccess_isDownloadingAndSuccessSequentially() {
        runTest {
            val resultList = listOf(
                Result.Loading,
                Result.Success(satelliteDetailDto)
            )
            whenever(getFakeSatelliteDetailUseCase.invoke(satelliteTestId)).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(SatelliteDetailState.Loading, SatelliteDetailState.Success(
                    satelliteDetailDto))
            val job = launch {
                satelliteDetailViewModel.state.toList(listOfEmittedResult)
            }
            satelliteDetailViewModel.getSatelliteDetail(satelliteTestId)
            Mockito.verify(getFakeSatelliteDetailUseCase).invoke(satelliteTestId)
            assert(listOfEmittedResult[1] is SatelliteDetailState.Success)
            job.cancel()
        }
    }

    @Test
    fun state_WhenUseCaseReturnsDownloadingAndError_isDownloadingAndErrorSequentially() {
        runTest {
            val resultList =
                listOf(Result.Loading, Result.Error(IOException()))
            whenever(getFakeSatelliteDetailUseCase.invoke(satelliteTestId)).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(
                    SatelliteDetailState.Loading,
                    SatelliteDetailState.Error(IOException())
                )
            val job = launch {
                satelliteDetailViewModel.state.toList(listOfEmittedResult)
            }
            satelliteDetailViewModel.getSatelliteDetail(satelliteTestId)
            Mockito.verify(getFakeSatelliteDetailUseCase).invoke(satelliteTestId)
            assert(listOfEmittedResult[1] is SatelliteDetailState.Error)
            job.cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}