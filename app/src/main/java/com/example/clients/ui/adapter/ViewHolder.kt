package com.example.clients.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.clients.data.Data
import com.example.clients.databinding.ClientListItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ViewHolder(private val binding: ClientListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Data) {
        binding.id.text = item.id
        binding.firstName.text = item.firstName
        binding.lastName.text = item.lastName
        binding.company.text = item.clientOrganisation.name
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK)
        binding.createdDate.text = item.created?.let { dateFormat.format(it) }
        binding.status.text = item.status
        binding.phone.text = item.fixedLinePhone
        binding.mobile.text = item.mobilePhone
        binding.email.text = item.email
    }
}
