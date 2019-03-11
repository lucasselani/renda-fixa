package com.example.encontre_sua_renda_fixa.features.bonds.presentation.activity

import android.os.Bundle
import com.example.encontre_sua_renda_fixa.R
import com.example.encontre_sua_renda_fixa.core.view.BaseActivity
import com.example.encontre_sua_renda_fixa.features.bonds.presentation.fragment.BondsFragment

class BondsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonds)
        replaceFragment(BondsFragment.newInstance(), R.id.container ,BondsFragment.TAG)
    }
}
