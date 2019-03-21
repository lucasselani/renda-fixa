package com.lambreta.rendafixa.features.bonds.presentation.widget

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view){
    abstract fun bind(item: Any)
}