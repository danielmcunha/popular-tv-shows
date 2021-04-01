package com.danielcunha.topseries.data.remote

sealed class Result<out T, out E> {

    data class Success<out T>(val value: T) : Result<T, Nothing>()
    data class Failure<out E>(val error: E) : Result<Nothing, E>()

    fun <C> fold(success: (T) -> C, failure: (E) -> C) {
        when (this) {
            is Success -> success(value)
            is Failure -> failure(error)
        }
    }

    fun takeSuccess(result: ((T) -> Unit)): Result<T, E> {
        return this.apply {
            if (this is Success) {
                result(value)
            }
        }
    }
}

suspend fun <T : Any> safe(block: suspend (() -> T)): SimpleResult<T> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Failure(e)
    }
}

typealias SimpleResult<T> = Result<T, Throwable>