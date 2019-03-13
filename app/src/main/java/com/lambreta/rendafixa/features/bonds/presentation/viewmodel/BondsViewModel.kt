package com.lambreta.rendafixa.features.bonds.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.extension.error
import com.lambreta.rendafixa.core.extension.loading
import com.lambreta.rendafixa.core.extension.success
import com.lambreta.rendafixa.core.functional.State
import com.lambreta.rendafixa.core.functional.None
import com.lambreta.rendafixa.core.view.BaseViewModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond
import com.lambreta.rendafixa.features.bonds.domain.usecase.GetBonds
import com.lambreta.rendafixa.features.bonds.presentation.exception.DealerFailure

class BondsViewModel(private val getBonds: GetBonds) : BaseViewModel() {

    var bonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var filteredBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var dealers: MutableLiveData<State<Map<String, List<Bond>>>> = MutableLiveData()
    var clickedDealer: MutableLiveData<String> = MutableLiveData()

    private var tryAgain: Boolean = true

    fun list() {
        dealers.loading(true)
        getBonds(None) { it.either(::handleFailure, ::handleSuccess) }
    }

    fun dealerClicked(dealer: String) { clickedDealer.value = dealer }

    fun filteredBonds() {
        dealers.value?.let {
            when(it) {
                is State.Success -> filteredBonds.success(it.data?.get(clickedDealer.value) ?: emptyList())
                is State.Error -> filteredBonds.error(DealerFailure())
            }
        }
    }

    private fun handleSuccess(data: Map<String, List<Bond>>?) {
        dealers.success(data)
        bonds.success(retrieveBonds(data))
        tryAgain = true
    }

    private fun handleFailure(failure: Failure?) {
        failure?.errorCode()?.let {
            if(it == 500 && tryAgain) {
                tryAgain = false
                list()
                return
            }
        }
        dealers.error(failure)
        bonds.error(failure)
    }

    private fun retrieveBonds(data: Map<String, List<Bond>>?) =
        arrayListOf<Bond>().apply {
            data?.let {
                it.values.forEach { list ->
                    this.addAll(list)
                }
            }
        }
}