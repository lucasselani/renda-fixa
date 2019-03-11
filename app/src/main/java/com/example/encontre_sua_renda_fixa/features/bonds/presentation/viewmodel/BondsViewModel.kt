package com.example.encontre_sua_renda_fixa.features.bonds.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.encontre_sua_renda_fixa.core.extension.isLoading
import com.example.encontre_sua_renda_fixa.core.extension.loading
import com.example.encontre_sua_renda_fixa.core.functional.State
import com.example.encontre_sua_renda_fixa.core.interactor.UseCase
import com.example.encontre_sua_renda_fixa.core.view.BaseViewModel
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond
import com.example.encontre_sua_renda_fixa.features.bonds.domain.usecase.GetBonds

class BondsViewModel(private val getBonds: GetBonds) : BaseViewModel() {

    var bonds: MutableLiveData<State<List<Bond>>> = MutableLiveData()

    val loading: Boolean
        get() = bonds.isLoading()

    fun list() {
        bonds.loading(true)
        getBonds(UseCase.None()) {
            bonds.loading(false)
            bonds.value = it
        }
    }

}