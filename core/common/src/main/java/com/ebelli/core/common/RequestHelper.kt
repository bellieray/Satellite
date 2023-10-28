package com.ebelli.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

suspend fun <T> flowCall(request: suspend () -> T): Flow<Result<T>> {
    return flow<Result<T>> {
        emit(Result.Success(request.invoke()))
    }.onStart { Result.Loading<T>() }.catch { emit(Result.Error()) }
}

sealed class Result<out T>(
    val data: T? = null,
    val error: Exception? = null
) {
    class Success<out T>(data: T?) : Result<T>(data)
    class Loading<out T> : Result<T>()
    class Error<T>(error: Exception? = null) : Result<T>(error = error)
}