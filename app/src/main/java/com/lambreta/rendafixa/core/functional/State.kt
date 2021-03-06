package com.lambreta.rendafixa.core.functional

import com.lambreta.rendafixa.core.exception.Failure

sealed class State<T> {
    data class Progress<T>(var loading: Boolean) : State<T>()
    data class Success<T>(var data: T?) : State<T>()
    data class Error<T>(val failure: Failure?) : State<T>()

    companion object {
        fun <T> loading(loading: Boolean): State<T> = Progress(loading)
        fun <T> success(data: T?): State<T> = Success(data)
        fun <T> error(failure: Failure?): State<T> = Error(failure)
    }
}
