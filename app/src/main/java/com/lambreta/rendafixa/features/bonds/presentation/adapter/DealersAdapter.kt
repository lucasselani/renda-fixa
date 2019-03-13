package com.lambreta.rendafixa.features.bonds.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambreta.rendafixa.databinding.ViewHolderDealersBinding

class DealersAdapter: RecyclerView.Adapter<DealersAdapter.DealerViewHolder>() {

    interface OnDealerListener {
        fun onDealerClicked(dealer: String)
    }

    private var listener: OnDealerListener? = null
    private var dealers: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderDealersBinding.inflate(inflater, parent, false)
        return DealerViewHolder(binding)
    }

    override fun getItemCount() = dealers.size

    override fun onBindViewHolder(holder: DealerViewHolder, position: Int) = holder.bind(dealers[position])

    fun update(dealers: Set<String>?) {
        dealers?.let {
            this.dealers.clear()
            this.dealers.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class DealerViewHolder(private val binding: ViewHolderDealersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dealer: String) =
            with(binding) {
                this.cardContainer.setOnClickListener { listener?.onDealerClicked(dealer) }
                this.dealer = dealer
                this.executePendingBindings()
            }
    }
}