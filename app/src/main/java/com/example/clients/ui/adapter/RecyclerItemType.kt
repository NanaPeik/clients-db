package com.example.clients.ui.adapter

enum class RecyclerItemType(private val i: Int) {
    SEARCH(0), HEADER(1), CLIENT(2);

    fun get(): Int {
        return this.i
    }
}