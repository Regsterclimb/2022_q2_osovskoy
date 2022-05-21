package com.example.a2022_q2_osovskoy.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ViewHolderStudentBinding
import com.example.a2022_q2_osovskoy.domain.entity.ListItem

class StudentViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.view_holder_student, parent, false)) {

    private val viewBinding by viewBinding(ViewHolderStudentBinding::bind)

    fun bind(listItem: ListItem, arrowOnClickAction: (name: String) -> Unit) {
        listItem as ListItem.StudentItem

        val fullName = String.format(listItem.name + " " + listItem.secondName)

        with(viewBinding) {
            personName.text = fullName
            personText.text = listItem.description
            setUpButton(this, listItem.hasPortfolio, arrowOnClickAction, fullName)
        }
    }

    private fun setUpButton(
        binding: ViewHolderStudentBinding,
        hasPortfolio: Boolean,
        onArrowClickAction: (name: String) -> Unit,
        itemName: String,
    ) {
        binding.arrow.apply {
            isVisible = hasPortfolio
            if (hasPortfolio) {
                setOnClickListener {
                    onArrowClickAction(itemName)
                }
            }
        }
    }
}