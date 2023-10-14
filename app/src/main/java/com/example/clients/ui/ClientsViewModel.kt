package com.example.clients.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clients.data.ClientsRepository
import com.example.clients.data.Data
import com.example.clients.data.contains
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(private val clientsRepository: ClientsRepository) :
    ViewModel() {

    private var ascending = true

    private var clientsList: MutableSharedFlow<List<Data>> = MutableStateFlow(listOf())

    var filteredClientList: MutableSharedFlow<List<Data>> = clientsList

    fun getClientsList() {
        viewModelScope.launch {
            clientsList = clientsRepository.fetchDataFromJsonAndStore()
            filteredClientList.emitAll(clientsList)
        }
    }


    fun filterByProperty(propertyType: PropertyEnum) {
        ascending = !ascending
        viewModelScope.launch {
            var list: List<Data>?

            when (propertyType) {
                PropertyEnum.ID -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.id })
                }

                PropertyEnum.FIRST_NAME -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.firstName })
                }

                PropertyEnum.LAST_NAME -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.lastName })
                }

                PropertyEnum.COMPANY -> {
                    list = clientsList.firstOrNull()
                        ?.sortedWith(compareBy { it.clientOrganisation.name })
                }

                PropertyEnum.CREATED_DATA -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.created })
                }

                PropertyEnum.STATUS -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.status })
                }

                PropertyEnum.PHONE -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.fixedLinePhone })
                }

                PropertyEnum.MOBILE -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.mobilePhone })
                }

                PropertyEnum.EMAIL -> {
                    list = clientsList.firstOrNull()?.sortedWith(compareBy { it.email })
                }

                else -> {
                    list = clientsList.firstOrNull()
                }
            }

            if (!ascending)
                list = list?.reversed()
            filteredClientList.emit(list ?: listOf())
        }
    }

    fun searchByProperty(searchText: String) {
        viewModelScope.launch {
            val list = mutableListOf<Data>()
            clientsList.firstOrNull()?.forEach { data ->
                if (data.contains(searchText.lowercase())) {
                    list.add(data)
                }
            }
            filteredClientList.emit(list)
        }
    }

}