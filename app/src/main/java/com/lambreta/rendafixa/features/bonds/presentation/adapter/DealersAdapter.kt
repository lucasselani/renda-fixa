package com.lambreta.rendafixa.features.bonds.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambreta.rendafixa.databinding.ViewHolderDealersBinding
import com.lambreta.rendafixa.core.functional.OnViewInteraction

class DealersAdapter : RecyclerView.Adapter<DealersAdapter.DealerViewHolder>() {

    private var dealers: ArrayList<String> = arrayListOf()
    private var listener: OnViewInteraction<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderDealersBinding.inflate(inflater, parent, false)
        return DealerViewHolder(binding)
    }

    override fun getItemCount() = dealers.size

    override fun onBindViewHolder(holder: DealerViewHolder, position: Int) = holder.bind(dealers[position])

    fun setListener(listener: OnViewInteraction<String>) {
        this.listener = listener
    }

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
                this.cardContainer.setOnClickListener { listener?.onClicked(dealer) }
                this.dealer = dealer
                this.executePendingBindings()
            }
    }
}