package com.example.clients.data

import java.util.Date

data class Data(
    val created: Date? = null,
    val email: String? = "",
    val firstName: String = "",
    val fixedLinePhone: String = "",
    val id: String = "",
    val lastName: String = "",
    val mobilePhone: String? = "",
    val status: String = "",
    val clientOrganisation: ClientOrganisation = ClientOrganisation("", "")
)

fun Data.contains(searchText: String) = id.contains(searchText) || firstName.contains(searchText)
        || lastName.contains(searchText) || fixedLinePhone.contains(searchText)
        || mobilePhone?.contains(searchText) == true || clientOrganisation.name.contains(searchText)
        || email?.contains(searchText) == true || status.contains(searchText)