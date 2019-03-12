package com.example.encontre_sua_renda_fixa.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val code: Int?) : Failure()
    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()

    fun errorCode() = if(this is Failure.ServerError) this.code else null
}