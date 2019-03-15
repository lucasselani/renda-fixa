package com.lambreta.rendafixa.features.bonds.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.adapter.FragmentPagerAdapter
import com.lambreta.rendafixa.core.view.BaseNavigationActivity
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import kotlinx.android.synthetic.main.activity_bonds.*
import kotlinx.android.synthetic.main.layout_appbar_tabs.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BondsActivity : BaseNavigationActivity() {

    private val viewModel: BondsViewModel by viewModel()
    private val fragmentsMap: Map<Int, Fragment> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonds)
        setupView()
    }

    private fun setupView() {
        setupActionBar(R.string.app_nickname, R.mipmap.ic_launcher)
        setupAdapter()
        setupViewModel()
        startAds()
    }

    private fun setupAdapter() {
        contentContainer.adapter = FragmentPagerAdapter(supportFragmentManager,
                fragmentsMap.values.toList(),
                fragmentsMap.keys.toList().map { getString(it) })
        contentContainer.offscreenPageLimit = 3
        tabs.setupWithViewPager(contentContainer)
    }

    private fun setupViewModel() {
        viewModel.list()
    }
}
