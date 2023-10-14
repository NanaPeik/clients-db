package com.example.clients.data

import com.example.clients.ui.PropertyEnum
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
) {
    fun getPropertyType(searchText: String): Pair<PropertyEnum, String> {
        return if (id.contains(searchText)) {
            Pair(PropertyEnum.ID, id)
        } else if (firstName.contains(searchText)) {
            Pair(PropertyEnum.FIRST_NAME, firstName)
        } else if (lastName.contains(searchText)) {
            Pair(PropertyEnum.LAST_NAME, lastName)
        } else if (fixedLinePhone.contains(searchText)) {
            Pair(PropertyEnum.PHONE, fixedLinePhone)
        } else if (mobilePhone?.contains(searchText) == true) {
            Pair(PropertyEnum.MOBILE, mobilePhone)
        } else if (clientOrganisation.name.contains(searchText)) {
            Pair(PropertyEnum.COMPANY, clientOrganisation.name)
        } else if (email?.contains(searchText) == true) {
            Pair(PropertyEnum.EMAIL, email)
        } else if (status.contains(searchText)) {
            Pair(PropertyEnum.STATUS, status)
        } else {
            Pair(PropertyEnum.NA, "")
        }
    }
}


fun Data.contains(searchText: String) =
    id.contains(searchText) || firstName.lowercase().contains(searchText)
            || lastName.lowercase().contains(searchText) || fixedLinePhone.lowercase()
        .contains(searchText)
            || mobilePhone?.lowercase()
        ?.contains(searchText) == true || clientOrganisation.name.lowercase().contains(searchText)
            || email?.lowercase()?.contains(searchText) == true || status.lowercase()
        .contains(searchText)