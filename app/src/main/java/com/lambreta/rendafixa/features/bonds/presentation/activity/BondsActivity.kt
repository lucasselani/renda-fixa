package com.lambreta.rendafixa.features.bonds.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.extension.showDialogFragment
import com.lambreta.rendafixa.core.model.Sort
import com.lambreta.rendafixa.core.view.adapter.FragmentPagerAdapter
import com.lambreta.rendafixa.core.view.activity.BaseAdMobActivity
import com.lambreta.rendafixa.core.view.dialog.SortDialog
import com.lambreta.rendafixa.features.bonds.presentation.enums.SortType
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import kotlinx.android.synthetic.main.activity_bonds.*
import kotlinx.android.synthetic.main.layout_appbar_tabs.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BondsActivity : BaseAdMobActivity(), SortDialog.OnDialogListener<SortType> {

    private val viewModel: BondsViewModel by viewModel()
    private val fragmentsMap: Map<Int, Fragment> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonds)
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu?) : Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.sort -> handleFilter()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSort(sort: Sort<SortType>?) = viewModel.sortByType(sort?.type)

    private fun setupView() {
        setupActionBar(R.string.app_nickname)
        setupAdapter()
        setupViewModel()
        startAds()
    }

    private fun setupAdapter() {
        viewPager.adapter = FragmentPagerAdapter(supportFragmentManager,
                fragmentsMap.values.toList(),
                fragmentsMap.keys.toList().map { getString(it) })
        viewPager.offscreenPageLimit = 3
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupViewModel() = viewModel.list()

    private fun handleFilter() = this.showDialogFragment(SortDialog.TAG, get<SortDialog<SortType>>())
}
