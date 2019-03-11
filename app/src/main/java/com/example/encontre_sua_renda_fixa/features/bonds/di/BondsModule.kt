package com.example.encontre_sua_renda_fixa.features.bonds.di

import com.example.encontre_sua_renda_fixa.features.bonds.constants.baseUrl
import com.example.encontre_sua_renda_fixa.core.di.network.BaseNetworkModule
import com.example.encontre_sua_renda_fixa.features.bonds.data.api.BondsApi
import com.example.encontre_sua_renda_fixa.features.bonds.data.service.BondsService
import com.example.encontre_sua_renda_fixa.features.bonds.domain.usecase.GetBonds
import com.example.encontre_sua_renda_fixa.features.bonds.presentation.adapter.BondsAdapter
import com.example.encontre_sua_renda_fixa.features.bonds.presentation.viewmodel.BondsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import com.example.encontre_sua_renda_fixa.features.bonds.data.model.Bond as BondDataModel
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDomainModel
import org.koin.dsl.module.module

private val bondsApi = BaseNetworkModule.createService(baseUrl).create(BondsApi::class.java)

val bondsModule = module {
    factory { GetBonds(BondsService(bondsApi, androidContext())) }
    viewModel { BondsViewModel(get()) }
}