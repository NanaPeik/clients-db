package com.example.clients.ui.adapter

import android.content.Context
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.clients.R
import com.example.clients.databinding.ClientListHeaderBinding
import com.example.clients.ui.PropertyEnum


class HeaderViewHolder(
    private val binding: ClientListHeaderBinding,
    private val filterWithProperty: (PropertyEnum) -> Unit,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {

    private var selectedParameterType = PropertyEnum.NA
    private var ascending = true

    fun bind() {
        binding.id.setOnClickListener {
            onPropertySelected(PropertyEnum.ID, binding.id)
        }
        binding.firstName.setOnClickListener {
            onPropertySelected(PropertyEnum.FIRST_NAME, binding.firstName)
        }
        binding.lastName.setOnClickListener {
            onPropertySelected(PropertyEnum.LAST_NAME, binding.lastName)
        }
        binding.company.setOnClickListener {
            onPropertySelected(PropertyEnum.COMPANY, binding.company)
        }
        binding.createdDate.setOnClickListener {
            onPropertySelected(PropertyEnum.CREATED_DATA, binding.createdDate)
        }
        binding.status.setOnClickListener {
            onPropertySelected(PropertyEnum.STATUS, binding.status)
        }
        binding.phone.setOnClickListener {
            onPropertySelected(PropertyEnum.PHONE, binding.phone)
        }
        binding.mobile.setOnClickListener {
            onPropertySelected(PropertyEnum.MOBILE, binding.mobile)
        }
        binding.email.setOnClickListener {
            onPropertySelected(PropertyEnum.EMAIL, binding.email)
        }
    }

    private fun setSelectedParameter(view: TextView) {
        view.setBackgroundColor(context.getColor(R.color.selected_sort_parameter_color))
        if (ascending)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, null,
                AppCompatResources.getDrawable(context, R.drawable.sort_ascending_ic), null
            )
        else view.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null,
            AppCompatResources.getDrawable(context, R.drawable.sort_descending_ic), null
        )
    }

    private fun setDefaultDrawable(view: TextView) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null,
            AppCompatResources.getDrawable(context, R.drawable.sort_ic), null
        )
    }

    private fun onPropertySelected(propertyType: PropertyEnum, view: TextView) {
        filterWithProperty.invoke(propertyType)
        if (propertyType != selectedParameterType) {
            ascending = true

            when (selectedParameterType) {
                PropertyEnum.ID -> {
                    binding.id.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.id)
                }

                PropertyEnum.FIRST_NAME -> {
                    binding.firstName.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.firstName)
                }

                PropertyEnum.LAST_NAME -> {
                    binding.lastName.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.lastName)
                }

                PropertyEnum.COMPANY -> {
                    binding.company.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.company)
                }

                PropertyEnum.CREATED_DATA -> {
                    binding.createdDate.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.createdDate)
                }

                PropertyEnum.STATUS -> {
                    binding.status.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.status)
                }

                PropertyEnum.PHONE -> {
                    binding.phone.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.phone)
                }

                PropertyEnum.MOBILE -> {
                    binding.mobile.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.mobile)
                }

                PropertyEnum.EMAIL -> {
                    binding.email.setBackgroundColor(context.getColor(R.color.transpatent))
                    setDefaultDrawable(binding.email)
                }

                else -> {}
            }
        } else {
            ascending = !ascending
        }
        setSelectedParameter(view)

        selectedParameterType = propertyType
    }
}