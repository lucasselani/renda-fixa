package com.lambreta.rendafixa.features.bonds.presentation.activity

import android.os.Bundle
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.view.BaseActivity
import com.lambreta.rendafixa.features.bonds.presentation.fragment.BondsFragment
import com.lambreta.rendafixa.features.bonds.presentation.fragment.DealersFragment
import com.lambreta.rendafixa.core.functional.OnViewInteraction
import com.lambreta.rendafixa.features.bonds.presentation.adapter.DealersAdapter
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BondsActivity : BaseActivity(), OnViewInteraction<String> {

    private val viewModel: BondsViewModel by viewModel()
    private val dealersAdapter: DealersAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonds)
        setupView()
    }

    override fun onClicked(item: String) {
        viewModel.dealerClicked(item)
        addFragment(BondsFragment.newInstance(), R.id.container, BondsFragment.TAG)
    }

    private fun setupView() {
        dealersAdapter.setListener(this)
        replaceFragment(DealersFragment.newInstance(), R.id.container, DealersFragment.TAG)
    }
}
