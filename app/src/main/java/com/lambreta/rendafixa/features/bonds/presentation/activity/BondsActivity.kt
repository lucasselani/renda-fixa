package com.lambreta.rendafixa.features.bonds.presentation.activity

import android.os.Bundle
import com.lambreta.rendafixa.core.view.BaseActivity
import com.lambreta.rendafixa.features.bonds.presentation.adapter.DealersAdapter
import com.lambreta.rendafixa.features.bonds.presentation.fragment.BondsFragment
import com.lambreta.rendafixa.features.bonds.presentation.fragment.DealersFragment
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel

class BondsActivity : BaseActivity(), DealersAdapter.OnDealerListener {

    private val viewModel by viewModel<BondsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonds)
        replaceFragment(DealersFragment.newInstance(), R.id.container, DealersFragment.TAG)
    }

    override fun onDealerClicked(dealer: String) {
        viewModel.dealerClicked(dealer)
        addFragment(BondsFragment.newInstance(), R.id.container, BondsFragment.TAG)
    }
}
