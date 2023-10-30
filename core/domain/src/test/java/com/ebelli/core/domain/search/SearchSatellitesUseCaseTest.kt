package com.ebelli.core.domain.search

import app.cash.turbine.test
import com.ebelli.core.domain.query
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import com.ebelli.core.common.Result
class SearchSatellitesUseCaseTest {

    private val fakeSearchSatellitesUseCase = FakeSearchSatellitesUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeSearchSatellitesUseCase(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            fakeSearchSatellitesUseCase(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            fakeSearchSatellitesUseCase.updateShowError(true)
            fakeSearchSatellitesUseCase(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Result.Error::class.java)
                awaitComplete()
            }
        }
    }
}