package com.ebelli.core.domain.satellite

import app.cash.turbine.test
import com.ebelli.core.common.Result
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetSatelliteUseCaseTest {

    private val fakeGetSatelliteUseCase = FakeGetSatelliteUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeGetSatelliteUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            fakeGetSatelliteUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            fakeGetSatelliteUseCase.updateShowError(true)
            fakeGetSatelliteUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Error::class.java)
                awaitComplete()
            }
        }
    }
}