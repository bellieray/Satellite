package com.ebelli.core.domain.detail

import app.cash.turbine.test
import com.ebelli.core.common.Result
import com.ebelli.core.domain.satelliteId
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetSatelliteDetailUseCaseTest {

    private val getSatelliteDetailUseCase = FakeGetSatelliteDetailUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            getSatelliteDetailUseCase(satelliteId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            getSatelliteDetailUseCase(satelliteId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getSatelliteDetailUseCase.updateShowError(true)
            getSatelliteDetailUseCase(satelliteId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Error::class.java)
                awaitComplete()
            }
        }
    }
}