package com.lambreta.rendafixa.features.bonds.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.extension.error
import com.lambreta.rendafixa.core.extension.loading
import com.lambreta.rendafixa.core.extension.success
import com.lambreta.rendafixa.core.functional.None
import com.lambreta.rendafixa.core.functional.State
import com.lambreta.rendafixa.core.view.viewmodel.BaseViewModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond
import com.lambreta.rendafixa.features.bonds.domain.usecase.GetBonds
import com.lambreta.rendafixa.features.bonds.presentation.enums.BondType
import com.lambreta.rendafixa.features.bonds.presentation.enums.SortType

class BondsViewModel(private val getBonds: GetBonds) : BaseViewModel() {

    private var bonds: Map<String, List<Bond>> = mapOf()
    var cdiBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var ipcaBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()
    var preIndexedBonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()

    private var tryAgain: Boolean = true
    private var loading: Boolean = false

    fun list() {
        loadAll()
        getBonds()
    }

    fun listByType(type: BondType) {
        when(type) {
            BondType.CDI -> cdiBonds.loading(true)
            BondType.IPCA -> ipcaBonds.loading(true)
            BondType.PRE -> preIndexedBonds.loading(true)
        }
        getBonds()
    }

    fun sortByType(type: SortType?) {
        loadAll()
        when(type) {
            SortType.INTEREST -> sort(compareByDescending { it.interest })
            SortType.MATURITYDATE -> sort(compareBy { it.maturityDays })
            SortType.MINAPPLICATION -> sort(compareBy { it.unitPrice })
            SortType.NAME -> sort(compareBy { it.dealer })
        }
    }

    private fun sort(sorting: Comparator<Bond>) {
        cdiBonds.success(bonds[BondType.CDI.value]?.sortedWith(sorting))
        ipcaBonds.success(bonds[BondType.IPCA.value]?.sortedWith(sorting))
        preIndexedBonds.success(bonds[BondType.PRE.value]?.sortedWith(sorting))
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

    private fun loadAll() {
        cdiBonds.loading(true)
        ipcaBonds.loading(true)
        preIndexedBonds.loading(true)
    }

    private fun getBonds() {
        if(!loading) {
            loading = true
            getBonds(None) {
                loading = false
                it.either(::handleFailure, ::handleSuccess)
            }
        }
    }
}