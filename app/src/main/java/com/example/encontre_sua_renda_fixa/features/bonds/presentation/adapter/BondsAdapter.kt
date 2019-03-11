package com.example.encontre_sua_renda_fixa.features.bonds.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encontre_sua_renda_fixa.databinding.ViewHolderBondsBinding
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond

class BondsAdapter: RecyclerView.Adapter<BondsAdapter.BondViewHolder>() {

    private var bonds: List<Bond> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBondsBinding.inflate(inflater, parent, false)
        return BondViewHolder(binding)
    }

    override fun getItemCount() = bonds.size

    override fun onBindViewHolder(holder: BondViewHolder, position: Int) = holder.bind(bonds[position])

    fun update(bonds: List<Bond>?) {
        this.bonds = bonds ?: emptyList()
        notifyDataSetChanged()
    }

    inner class BondViewHolder(private val binding: ViewHolderBondsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bond: Bond) {
            binding.bond = bond
            binding.executePendingBindings()
        }
    }
}