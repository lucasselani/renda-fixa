package com.lambreta.rendafixa.features.bonds.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.extension.gone
import com.lambreta.rendafixa.core.extension.visible
import com.lambreta.rendafixa.core.functional.State
import com.lambreta.rendafixa.core.view.BaseFragment
import com.lambreta.rendafixa.databinding.FragmentListBinding
import com.lambreta.rendafixa.features.bonds.presentation.adapter.DealersAdapter
import com.lambreta.rendafixa.features.bonds.presentation.viewmodel.BondsViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DealersFragment : BaseFragment() {

    private val viewModel: BondsViewModel by sharedViewModel()
    private val adapter: DealersAdapter by inject()

    companion object {
        val TAG = DealersFragment::class.qualifiedName

        @JvmStatic
        fun newInstance() = DealersFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setupViewModel()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
    }

    private fun setupViewModel() {
        viewModel.list()
        viewModel.dealers.observe(this, Observer {
            when(it) {
                is State.Success -> adapter.update(it.data?.keys)
                is State.Progress -> handleProgress(it.loading)
                is State.Error -> handleError(it.failure)
            }
        })
    }

    private fun handleProgress(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
        if(loading) {
            recyclerView.gone()
            shimmer.visible()
        } else {
            recyclerView.visible()
            shimmer.gone()
        }
    }

    private fun handleError(failure: Failure?) {
        when(failure) {
            is Failure.NetworkConnection -> baseActivity.showSnackbar(rootView, R.string.no_internet)
            is Failure.ServerError -> baseActivity.showSnackbar(rootView, R.string.list_error)
        }
    }
}