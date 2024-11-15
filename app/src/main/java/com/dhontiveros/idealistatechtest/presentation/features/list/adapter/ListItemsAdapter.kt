package com.dhontiveros.idealistatechtest.presentation.features.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.dhontiveros.idealistatechtest.databinding.RowListItemBinding
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.listadapter.BaseRecyclerAdapter

class ListItemsAdapter (
    private val onChange: ((MutableList<PropertyListItem>) -> Unit)? = null,
    private val onClick: ((PropertyListItem) -> Unit)? = null,
    private val onFavUpdate: ((PropertyListItem, Int) -> Unit)? = null
) : BaseRecyclerAdapter<PropertyListItem, RowListItemBinding, ListItemViewHolder>(PropertyItemDiffUtil()){

    override fun onChangedList(
        previousList: MutableList<PropertyListItem>,
        currentList: MutableList<PropertyListItem>
    ) {
        onChange?.invoke(currentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = RowListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ListItemViewHolder(binding, onClick, onFavUpdate)
    }

    fun updateItem(indexPos: Int, isFav: Boolean, dateSaveFav: Long?){
        val result = currentList.toMutableList().apply {
            val updatedItem = this[indexPos].copy(isFavorite = isFav, dateFav = dateSaveFav)
            this[indexPos] = updatedItem
        }
        submitList(result)
    }

    fun removeItem(index: Int){
        val result = currentList.toMutableList().also { it.removeAt(index) }
        submitList(result)
    }
}


class PropertyItemDiffUtil : DiffUtil.ItemCallback<PropertyListItem>() {

    override fun areItemsTheSame(oldItem: PropertyListItem, newItem: PropertyListItem): Boolean =
        oldItem.propertyCode == newItem.propertyCode

    override fun areContentsTheSame(oldItem: PropertyListItem, newItem: PropertyListItem): Boolean =
        oldItem == newItem
}