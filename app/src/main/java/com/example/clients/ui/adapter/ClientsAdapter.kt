package com.example.clients.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clients.data.Data
import com.example.clients.databinding.ClientListHeaderBinding
import com.example.clients.databinding.ClientListItemBinding
import com.example.clients.databinding.ClientSearchItemBinding
import com.example.clients.ui.PropertyEnum

class ClientsAdapter(
    private val context: Context,
    private val filterWithProperty: (PropertyEnum) -> Unit,
    private val search: (String) -> Unit
) :
    ListAdapter<Data, RecyclerView.ViewHolder>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerItemType.SEARCH.get() -> {
                SearchViewHolder(
                    ClientSearchItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            RecyclerItemType.HEADER.get() -> HeaderViewHolder(
                ClientListHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ), filterWithProperty, context
            )

            else -> ViewHolder(
                ClientListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchViewHolder -> holder.bind(search)
            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> RecyclerItemType.SEARCH.get()
            1 -> RecyclerItemType.HEADER.get()
            else -> RecyclerItemType.CLIENT.get()
        }
    }

}