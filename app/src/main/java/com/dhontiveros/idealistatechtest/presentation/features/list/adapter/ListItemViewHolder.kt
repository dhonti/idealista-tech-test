package com.dhontiveros.idealistatechtest.presentation.features.list.adapter

import com.dhontiveros.idealistatechtest.databinding.RowListItemBinding
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.listadapter.BaseViewHolder

class ListItemViewHolder(
    private val binding: RowListItemBinding,
    private val onClick: ((PropertyListItem) -> Unit)? = null,
) : BaseViewHolder<PropertyListItem, RowListItemBinding>(binding) {

    init {
        binding.root.setOnClickListener {
            getRowItem()?.let { onClick?.invoke(it) }
        }
    }

    override fun bind() {
        getRowItem()?.let { item ->
            binding.item = item
            binding.executePendingBindings()
        }
    }

}