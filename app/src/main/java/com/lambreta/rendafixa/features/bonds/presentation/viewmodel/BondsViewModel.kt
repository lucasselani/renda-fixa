package com.lambreta.rendafixa.features.bonds.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.extension.error
import com.lambreta.rendafixa.core.extension.loading
import com.lambreta.rendafixa.core.extension.success
import com.lambreta.rendafixa.core.functional.None
import com.lambreta.rendafixa.core.functional.State
import com.lambreta.rendafixa.core.view.BaseViewModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond
import com.lambreta.rendafixa.features.bonds.domain.usecase.GetBonds
import com.lambreta.rendafixa.features.bonds.presentation.enum.BondType

class BondsViewModel(private val getBonds: GetBonds) : BaseViewModel() {

    private var bonds: Map<String, List<Bond>> = mapOf()
    var cdiBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var ipcaBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var preIndexedBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()

    private var tryAgain: Boolean = true

    fun list() {
        cdiBonds.loading(true)
        ipcaBonds.loading(true)
        preIndexedBonds.loading(true)
        getBonds(None) { it.either(::handleFailure, ::handleSuccess) }
    }

    private fun handleSuccess(data: List<Bond>?) {
        data?.let { list ->
            bonds = list.groupBy { it.index }
        }
        cdiBonds.success(bonds[BondType.CDI.value]?.sortedByDescending { it.interest })
        ipcaBonds.success(bonds[BondType.IPCA.value]?.sortedByDescending { it.interest })
        preIndexedBonds.success(bonds[BondType.PRE.value]?.sortedByDescending { it.interest })
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
        cdiBonds.error(failure)
        ipcaBonds.error(failure)
        preIndexedBonds.error(failure)
    }
}