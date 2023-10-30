package com.ebelli.dashboard

import com.ebelli.core.common.Result
import com.ebelli.core.domain.usecase.satellite.GetSatellitesUseCase
import com.ebelli.core.domain.usecase.search.SearchSatellitesUseCase
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
class SatellitesViewModelTest {
    @Mock
    private lateinit var getSatellitesUseCase: GetSatellitesUseCase

    @Mock
    private lateinit var searchSatellitesUseCase: SearchSatellitesUseCase


    private lateinit var satellitesViewModel: SatellitesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        satellitesViewModel = SatellitesViewModel(
            getSatellitesUseCase,
            searchSatellitesUseCase
        )
    }

    @Test
    fun state_WhenGetSatellitesUseCaseReturnsDownloading_isDownloading() {
        runTest {
            val resultList = listOf(Result.Loading)
            whenever(getSatellitesUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult = mutableListOf<SatellitesState>(
                SatellitesState.Loading
            )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            Mockito.verify(getSatellitesUseCase).invoke()
            assert(listOfEmittedResult.first() is SatellitesState.Loading)
            job.cancel()
        }
    }


    @Test
    fun state_WhenGetSatellitesUseCaseReturnsDownloadingAndSuccess_isDownloadingAndSuccessSequentially() {
        runTest {
            val resultList = listOf(
                Result.Loading,
                Result.Success(satelliteList)
            )
            whenever(getSatellitesUseCase.invoke()).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(
                    SatellitesState.Loading, SatellitesState.Success(satelliteList)
                )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            Mockito.verify(getSatellitesUseCase).invoke()
            assert(listOfEmittedResult[1] is SatellitesState.Success)
            job.cancel()
        }
    }

    @Test
    fun state_WhenGetSatellitesUseCaseReturnsDownloadingAndError_isDownloadingAndErrorSequentially() {
        runTest {
            val resultList =
                listOf(Result.Loading, Result.Error(IOException()))
            whenever(getSatellitesUseCase.invoke()).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(
                    SatellitesState.Loading,
                    SatellitesState.Error(IOException())
                )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            assert(listOfEmittedResult[1] is SatellitesState.Error)
            job.cancel()
        }
    }

    @Test
    fun state_WhenSearchSatellitesUseCaseReturnsDownloading_isDownloading() {
        runTest {
            val resultList = listOf(Result.Loading)
            whenever(getSatellitesUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult = mutableListOf<SatellitesState>(
                SatellitesState.Loading
            )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            satellitesViewModel.makeSearch(query)
            Mockito.verify(searchSatellitesUseCase).invoke(query)
            assert(listOfEmittedResult.first() is SatellitesState.Loading)
            job.cancel()
        }
    }


    @Test
    fun state_WhenSearchSatellitesUseCaseReturnsDownloadingAndSuccess_isDownloadingAndSuccessSequentially() {
        runTest {
            val resultList = listOf(
                Result.Loading,
                Result.Success(searchedSatelliteList)
            )
            whenever(getSatellitesUseCase.invoke()).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(
                    SatellitesState.Loading, SatellitesState.Success(searchedSatelliteList)
                )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            satellitesViewModel.makeSearch(query)
            Mockito.verify(searchSatellitesUseCase).invoke(query)
            assert(listOfEmittedResult[1] is SatellitesState.Success)
            job.cancel()
        }
    }

    @Test
    fun state_WhenSearchSatellitesUseCaseReturnsDownloadingAndError_isDownloadingAndErrorSequentially() {
        runTest {
            val resultList =
                listOf(Result.Loading, Result.Error(IOException()))
            whenever(searchSatellitesUseCase.invoke(query)).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf(
                    SatellitesState.Loading,
                    SatellitesState.Error(IOException())
                )
            val job = launch {
                satellitesViewModel.state.toList(listOfEmittedResult)
            }
            satellitesViewModel.fetchSatellites()
            Mockito.verify(searchSatellitesUseCase).invoke(query)
            assert(listOfEmittedResult[1] is SatellitesState.Error)
            job.cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}