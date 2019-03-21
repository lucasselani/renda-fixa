package com.lambreta.rendafixa.core.repository

import android.content.Context
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.extension.networkInfo
import com.lambreta.rendafixa.core.functional.Either
import retrofit2.Call
import timber.log.Timber

abstract class NetworkRepository {
    inline fun <T, R> request(context: Context,
                              call: Call<T>,
                              transform: (T) -> R,
                              default: T): Either<Failure, R> {
        return when(context.networkInfo?.isConnected) {
            true ->
                return try {
                    val response = call.execute()
                    when (response.isSuccessful) {
                        true -> Either.Right(transform((response.body() ?: default)))
                        false -> Either.Left(Failure.ServerError(response.code()))
                    }
                } catch (exception: Throwable) {
                    Timber.e(exception)
                    return Either.Left(Failure.ServerError(null))
                }
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }
}