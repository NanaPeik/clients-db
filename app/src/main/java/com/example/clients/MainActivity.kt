package com.example.clients

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clients.data.Data
import com.example.clients.databinding.ActivityMainBinding
import com.example.clients.ui.ClientsViewModel
import com.example.clients.ui.PropertyEnum
import com.example.clients.ui.adapter.ClientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val clientsViewModel: ClientsViewModel by viewModels()
    private lateinit var clientsAdapter: ClientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
    }

    private fun setAdapter() {
        var text: String
        clientsAdapter = ClientsAdapter("", this, { property: PropertyEnum ->
            text = ""
            clientsViewModel.filterByProperty(property)
        }) { searchText: String ->
            text = searchText
            clientsAdapter.setSearchText(text)
            clientsViewModel.searchByProperty(searchText)
        }

        binding.clientsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.clientsRv.adapter = clientsAdapter

        clientsViewModel.getClientsList()

        lifecycleScope.launch {
            clientsViewModel.filteredClientList.collectLatest {
                val clientsList = mutableListOf(Data(), Data())
                clientsList.addAll(it)
                clientsAdapter.submitList(clientsList)
            }
        }
    }
}