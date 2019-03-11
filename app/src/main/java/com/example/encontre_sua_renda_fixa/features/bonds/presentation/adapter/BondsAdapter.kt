package com.example.encontre_sua_renda_fixa.features.bonds.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.encontre_sua_renda_fixa.R
import com.example.encontre_sua_renda_fixa.databinding.ViewHolderBondsBinding
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond

class BondsAdapter(private var bonds: List<Bond>):
    RecyclerView.Adapter<BondsAdapter.BondViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewHolderBondsBinding =
            DataBindingUtil.inflate(inflater, R.layout.view_holder_bonds, parent, false)
        return BondViewHolder(binding)
    }

    override fun getItemCount() = bonds.size

    override fun onBindViewHolder(holder: BondViewHolder, position: Int) = holder.bind(bonds[position])

    fun notifyBonds(bonds: List<Bond>?) {
        this.bonds = bonds ?: emptyList()
        notifyDataSetChanged()
    }

    inner class BondViewHolder(val view: ViewHolderBondsBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(bond: Bond) { view.bond = bond }
    }
}