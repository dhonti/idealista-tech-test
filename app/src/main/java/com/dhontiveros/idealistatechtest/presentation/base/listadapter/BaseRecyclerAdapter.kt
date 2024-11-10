package com.dhontiveros.idealistatechtest.presentation.base.listadapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<Model : Any, VB : ViewBinding, VH : BaseViewHolder<Model, VB>>(
    callback: DiffUtil.ItemCallback<Model>
) : ListAdapter<Model, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.applyBindings( getItem(position) )
        holder.bind()
    }

    override fun submitList(items: List<Model>?){
        super.submitList(items ?: emptyList())
    }

    override fun onCurrentListChanged(
        previousList: MutableList<Model>,
        currentList: MutableList<Model>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        onChangedList(previousList,currentList)
    }

    abstract fun onChangedList(
        previousList: MutableList<Model>,
        currentList: MutableList<Model>
    )

}