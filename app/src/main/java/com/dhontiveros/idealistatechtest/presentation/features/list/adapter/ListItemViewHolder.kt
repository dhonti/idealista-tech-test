package com.dhontiveros.idealistatechtest.presentation.features.list.adapter

import coil.load
import com.dhontiveros.idealistatechtest.databinding.RowListItemBinding
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.listadapter.BaseViewHolder

class ListItemViewHolder(
    private val binding: RowListItemBinding,
    private val onClick: ((PropertyListItem) -> Unit)? = null,
    private val onFavUpdate: ((PropertyListItem, Int) -> Unit)? = null,
) : BaseViewHolder<PropertyListItem, RowListItemBinding>(binding) {

    init {
        binding.root.setOnClickListener {
            getRowItem()?.let { onClick?.invoke(it) }
        }
        binding.btFav.setOnClickListener{
            getRowItem()?.let { onFavUpdate?.invoke(it, adapterPosition) }
        }
    }

    override fun bind() {
        getRowItem()?.let { item ->
            binding.item = item
            binding.ivItem.load(item.thumbnail)
            binding.executePendingBindings()
        }
    }

}