package com.example.clients.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.clients.databinding.ClientSearchItemBinding

class SearchViewHolder(
    private val binding: ClientSearchItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(search: (String) -> Unit) {
        binding.getResult.setOnClickListener {
            search.invoke(binding.searchEt.text.toString())
        }
    }
}