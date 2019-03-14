package com.lambreta.rendafixa.features.bonds.di

import androidx.fragment.app.Fragment
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.platform.WebServiceHandler
import com.lambreta.rendafixa.features.bonds.constants.baseUrl
import com.lambreta.rendafixa.features.bonds.data.api.BondsApi
import com.lambreta.rendafixa.features.bonds.data.service.BondsService
import com.lambreta.rendafixa.features.bonds.domain.repository.BondsRepository
import com.lambreta.rendafixa.features.bonds.domain.usecase.GetBonds
import com.lambreta.rendafixa.features.bonds.presentation.adapter.BondsAdapter
import com.lambreta.rendafixa.features.bonds.presentation.enum.BondType
import com.lambreta.rendafixa.features.bonds.presentation.fragment.BondsFragment
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import com.lambreta.rendafixa.features.bonds.data.model.Bond as BondDataModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond as BondDomainModel

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
    viewModel { BondsViewModel(get()) }
    factory { BondsAdapter() }
}

val fragmentModule = module {
    single<Map<Int, Fragment>> {
        mapOf(R.string.cdi_fragment to BondsFragment.newInstance(BondType.CDI),
            R.string.ipca_fragment to BondsFragment.newInstance(BondType.IPCA),
            R.string.pre_index_fragment to BondsFragment.newInstance(BondType.PRE))
    }
}