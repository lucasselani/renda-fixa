package com.example.encontre_sua_renda_fixa.features.bonds.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.encontre_sua_renda_fixa.R
import com.example.encontre_sua_renda_fixa.core.extension.gone
import com.example.encontre_sua_renda_fixa.core.extension.visible
import com.example.encontre_sua_renda_fixa.core.functional.State
import com.example.encontre_sua_renda_fixa.core.view.BaseFragment
import com.example.encontre_sua_renda_fixa.databinding.FragmentBondsBinding
import com.example.encontre_sua_renda_fixa.features.bonds.presentation.adapter.BondsAdapter
import com.example.encontre_sua_renda_fixa.features.bonds.presentation.viewmodel.BondsViewModel
import kotlinx.android.synthetic.main.fragment_bonds.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class BondsFragment : BaseFragment() {

    private val viewModel: BondsViewModel by viewModel()
    private val adapter: BondsAdapter by lazy { BondsAdapter() }

    companion object {
        val TAG = BondsFragment::class.qualifiedName

        @JvmStatic
        fun newInstance() = BondsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentBondsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bonds, container,false)
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
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
    }

    private fun setupViewModel() {
        viewModel.list()
        viewModel.bonds.observe(this, Observer {
            when(it) {
                is State.Success -> adapter.update(it.data)
                is State.Progress -> handleProgress(it.loading)
                is State.Error -> baseActivity.showSnackbar(rootView, R.string.list_error)
            }
        })
    }

    private fun handleProgress(loading: Boolean) {
        if(loading) {
            recyclerView.gone()
            shimmer.visible()
        } else {
            recyclerView.visible()
            shimmer.gone()
        }
    }
}