package com.ebelli.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

 fun <T> flowCall(request: suspend () -> T?): Flow<Result<T>> {
    return flow<Result<T>> {
        emit(Result.Success(request.invoke()))
    }.onStart { Result.Loading }.catch {
        emit(Result.Error(it as? Exception)) }
}

sealed class Result<out T>(
    val data: T? = null,
    val error: Exception? = null
) {
    class Success<out T>(data: T?) : Result<T>(data)
    object Loading : Result<Nothing>()
    class Error<T>(error: Exception? = null) : Result<T>(error = error)
}