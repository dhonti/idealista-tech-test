package com.dhontiveros.idealistatechtest.presentation.base.listadapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<Model, VB : ViewBinding> constructor(viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: Model? = null

    fun applyBindings(data: Model?){
        item = data
    }

    abstract fun bind()

    fun getRowItem(): Model?{
        return item
    }

}