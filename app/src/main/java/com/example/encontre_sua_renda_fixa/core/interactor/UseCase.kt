package com.example.encontre_sua_renda_fixa.core.interactor

import com.example.encontre_sua_renda_fixa.core.exception.Failure
import com.example.encontre_sua_renda_fixa.core.functional.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): State<Type>

    operator fun invoke(params: Params,
                        onSuccess: ((Type?) -> Unit)? = null,
                        onError: ((Failure?) -> Unit)? = null) {
        val job = GlobalScope.async(Dispatchers.Default) { run(params) }
        GlobalScope.launch(Dispatchers.Main) {
            val result = job.await()
            if(result is State.Success) onSuccess?.invoke(result.data)
            else if(result is State.Error) onError?.invoke(result.failure)
        }
    }
}