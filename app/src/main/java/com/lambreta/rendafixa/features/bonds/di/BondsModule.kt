package com.lambreta.rendafixa.features.bonds.di

import com.lambreta.rendafixa.features.bonds.constants.baseUrl
import com.lambreta.rendafixa.core.platform.WebServiceHandler
import com.lambreta.rendafixa.features.bonds.data.api.BondsApi
import com.lambreta.rendafixa.features.bonds.data.service.BondsService
import com.lambreta.rendafixa.features.bonds.domain.repository.BondsRepository
import com.lambreta.rendafixa.features.bonds.domain.usecase.GetBonds
import com.lambreta.rendafixa.features.bonds.presentation.adapter.BondsAdapter
import com.lambreta.rendafixa.features.bonds.presentation.adapter.DealersAdapter
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import com.lambreta.rendafixa.features.bonds.data.model.Bond as BondDataModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond as BondDomainModel
import org.koin.dsl.module.module

val bondsModule = module {
    factory(name = "bondsApi") {
        WebServiceHandler().createService<BondsApi>(baseUrl, androidContext())
    }
    factory<BondsRepository>(name = "bondsService") {
        BondsService(get(), androidContext())
    }
    factory(name = "bondsUseCase") {
        GetBonds(get())
    }
}

val viewModule = module {
    single { BondsAdapter() }
    single { DealersAdapter() }
    viewModel { BondsViewModel(get()) }
}