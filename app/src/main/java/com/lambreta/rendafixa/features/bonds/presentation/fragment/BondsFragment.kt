package com.lambreta.rendafixa.features.bonds.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.extension.gone
import com.lambreta.rendafixa.core.extension.visible
import com.lambreta.rendafixa.core.functional.State
import com.lambreta.rendafixa.core.view.BaseFragment
import com.lambreta.rendafixa.databinding.FragmentBondsBinding
import com.lambreta.rendafixa.features.bonds.domain.model.Bond
import com.lambreta.rendafixa.features.bonds.presentation.adapter.BondsAdapter
import com.lambreta.rendafixa.features.bonds.presentation.enums.BondType
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import kotlinx.android.synthetic.main.fragment_bonds.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BondsFragment : BaseFragment() {

    companion object {
        fun newInstance(type: BondType) = BondsFragment().apply { this.type = type }
    }

    private val viewModel: BondsViewModel by sharedViewModel()
    private val adapter: BondsAdapter by inject()
    lateinit var type: BondType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentBondsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bonds, container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.bondType = type
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
    }

    private fun setupViewModel() {
        when(type) {
            BondType.CDI -> viewModel.cdiBonds.observe(this, observer())
            BondType.IPCA -> viewModel.ipcaBonds.observe(this, observer())
            BondType.PRE -> viewModel.preIndexedBonds.observe(this, observer())
        }
    }

    private fun observer() =
        Observer<State<List<Bond>>> {
            when(it) {
                is State.Success -> handleSuccess(it.data ?: emptyList())
                is State.Progress -> handleProgress(it.loading)
                is State.Error -> handleError(it.failure)
            }

        }

    private fun handleSuccess(bonds: List<Bond>) {
        adapter.update(bonds)
        if(bonds.isEmpty()) showView(noDataContainer)
        else showView(recyclerView)
    }

    private fun handleProgress(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
        if (loading) showView(shimmer)
        else showView(recyclerView)
    }

    private fun showView(view: View) {
        recyclerView.gone()
        shimmer.gone()
        noDataContainer.gone()
        view.visible()
    }
}