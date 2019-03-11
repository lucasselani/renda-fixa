package com.example.encontre_sua_renda_fixa.features.bonds.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encontre_sua_renda_fixa.R
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond
import kotlinx.android.synthetic.main.view_holder_bonds.view.*

class BondsAdapter(private var bonds: List<Bond>,
                   private val context: Context):
    RecyclerView.Adapter<BondsAdapter.BondViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BondViewHolder =
        BondViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_bonds, parent, false))

    override fun getItemCount() = bonds.size

    override fun onBindViewHolder(holder: BondViewHolder, position: Int) {
        holder.bind(bonds[position])
    }

    fun notifyBonds(bonds: List<Bond>?) {
        this.bonds = bonds ?: emptyList()
        notifyDataSetChanged()
    }

    inner class BondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bond: Bond) {
            with(itemView) {
               issuer.text = bond.issuer
                liquidity.text = bond.liquidity
                maturityDate.text = bond.maturityDate
                interest.text = bond.interest.toString()
                dealer.text = bond.dealer
            }
        }
    }
}