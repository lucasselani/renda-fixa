package com.lambreta.rendafixa.core.extension

import androidx.lifecycle.MutableLiveData
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.functional.State

fun <T> MutableLiveData<State<T>>.loading(loading: Boolean) {
    this.value = State.loading(loading)
}

fun <T> MutableLiveData<State<T>>.error(failure: Failure?) =
    with(this) {
        loading(false)
        this.value = State.error(failure)
    }

fun <T> MutableLiveData<State<T>>.success(data: T?) =
    with(this) {
        loading(false)
        this.value = State.success(data)
    }

fun <T> MutableLiveData<State<T>>.isLoading(): Boolean {
    var loading = false
    this.value?.let {
        if(it is State.Progress) loading = true
    }
    return loading
}
