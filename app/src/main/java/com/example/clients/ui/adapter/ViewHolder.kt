package com.example.clients.ui.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.clients.R
import com.example.clients.data.Data
import com.example.clients.databinding.ClientListItemBinding
import com.example.clients.ui.PropertyEnum
import java.text.SimpleDateFormat
import java.util.Locale

class ViewHolder(private val binding: ClientListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Data, charSequence: String, context: Context) {
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

        if (charSequence.isNotEmpty()) {
            val propertyType = item.getPropertyType(charSequence)
            val view = getView(propertyType.first)
            if (view != null) {
                styleSearchStringSnippet(propertyType.second, charSequence, context, view)
            }
        }
    }

    private fun styleSearchStringSnippet(
        item: String,
        charSequence: String,
        context: Context,
        view: TextView
    ) {
        if (charSequence.isNotEmpty() && item.lowercase().contains(charSequence)) {
            val startPos: Int = item.lowercase(Locale.ROOT).indexOf(
                charSequence.lowercase(
                    Locale.getDefault()
                )
            )
            val endPos: Int = startPos + charSequence.length
            if (startPos != -1 && endPos != -1) {
                val spannable: Spannable = SpannableString(item)
                spannable.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(context, R.color.purplish_blue)
                    ), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                view.text = spannable
            }
        } else
            view.text = item
    }

    private fun getView(propertyType: PropertyEnum): TextView? =
        when (propertyType) {
            PropertyEnum.ID -> binding.id
            PropertyEnum.FIRST_NAME -> binding.firstName
            PropertyEnum.LAST_NAME -> binding.lastName
            PropertyEnum.COMPANY -> binding.company
            PropertyEnum.EMAIL -> binding.email
            PropertyEnum.STATUS -> binding.status
            PropertyEnum.MOBILE -> binding.mobile
            PropertyEnum.PHONE -> binding.phone
            PropertyEnum.CREATED_DATA -> binding.createdDate
            PropertyEnum.NA -> {
                null
            }
        }

}
