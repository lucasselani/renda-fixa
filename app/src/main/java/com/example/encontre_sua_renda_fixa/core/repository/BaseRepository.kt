package com.example.encontre_sua_renda_fixa.core.repository

import android.content.Context
import android.util.Log
import com.example.encontre_sua_renda_fixa.core.exception.Failure
import com.example.encontre_sua_renda_fixa.core.extension.networkInfo
import com.example.encontre_sua_renda_fixa.core.functional.State
import retrofit2.Call

abstract class BaseRepository {
    inline fun <T, R> request(context: Context, call: Call<T>, transform: (T) -> R, default: T): State<R> {
        return when(context.networkInfo?.isConnected) {
            true ->
                return try {
                    val response = call.execute()
                    when (response.isSuccessful) {
                        true -> State.success(transform((response.body() ?: default)))
                        false -> State.error(Failure.ServerError(response.code()))
                    }
                } catch (exception: Throwable) {
                    exception.printStackTrace()
                    return State.error(Failure.ServerError(null))
                }
            false, null -> State.error(Failure.NetworkConnection)
        }
    }
}