package com.example.encontre_sua_renda_fixa.features.bonds.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.encontre_sua_renda_fixa.core.exception.Failure
import com.example.encontre_sua_renda_fixa.core.extension.error
import com.example.encontre_sua_renda_fixa.core.extension.loading
import com.example.encontre_sua_renda_fixa.core.extension.success
import com.example.encontre_sua_renda_fixa.core.functional.State
import com.example.encontre_sua_renda_fixa.core.functional.None
import com.example.encontre_sua_renda_fixa.core.view.BaseViewModel
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond
import com.example.encontre_sua_renda_fixa.features.bonds.domain.usecase.GetBonds

class BondsViewModel(private val getBonds: GetBonds) : BaseViewModel() {

    var tryAgain: Boolean = true
    var bonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()

    fun list() {
        bonds.loading(true)
        getBonds(None) { it.either(::handleFailure, ::handleSuccess) }
    }

    private fun handleSuccess(data: List<Bond>?) {
        bonds.success(data)
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
        bonds.error(failure)
    }

}