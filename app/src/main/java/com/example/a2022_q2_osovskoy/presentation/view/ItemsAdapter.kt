package com.example.a2022_q2_osovskoy.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.data.storage.ItemsData
import com.example.a2022_q2_osovskoy.databinding.ViewHolderBannerBinding
import com.example.a2022_q2_osovskoy.databinding.ViewHolderStudentBinding

class ItemsAdapter(
    private val onAcceptClick: (accept: String) -> Unit,
    private val onDeclineClick: (decline: String) -> Unit,
    private val arrowOnClickAction: (name: String) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val BANNER_TYPE = 0
        const val STUDENT_TYPE = 1
    }

    var items: List<ItemsData.ListItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            BANNER_TYPE -> BannerViewHolder(parent)
            STUDENT_TYPE -> StudentViewHolder(parent)
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is BannerViewHolder -> {
                holder.bind(
                    items[position],
                    onAcceptClick = onAcceptClick,
                    onDeclineClick = onDeclineClick
                )
            }
            is StudentViewHolder -> holder.bind(items[position],
                arrowOnClickAction = arrowOnClickAction)

            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int): Int =
        if (items[position] is ItemsData.ListItem.BannerItem) {
            BANNER_TYPE
        } else {
            STUDENT_TYPE
        }
}

class BannerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner, parent, false)
) {
    private val viewBinding by viewBinding(ViewHolderBannerBinding::bind)

    fun bind(
        listItem: ItemsData.ListItem,
        onAcceptClick: (accept: String) -> Unit,
        onDeclineClick: (decline: String) -> Unit,
    ) {
        listItem as ItemsData.ListItem.BannerItem
        with(viewBinding) {
            acceptButton.setOnClickListener {
                onAcceptClick(acceptButton.text.toString())
            }
            declineButton.setOnClickListener {
                onDeclineClick(declineButton.text.toString())
            }
            textReq.text = listItem.description
            newReq.text = listItem.title
        }
    }
}

class StudentViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_student, parent, false)
    ) {
    private val viewBinding by viewBinding(ViewHolderStudentBinding::bind)

    fun bind(listItem: ItemsData.ListItem, arrowOnClickAction: (name: String) -> Unit) {
        listItem as ItemsData.ListItem.StudentItem
        val hasPortfolio = listItem.hasPortfolio
        val fullName = listItem.name + " " + listItem.secondName

        with(viewBinding) {
            personName.text = fullName
            personText.text = listItem.description
            arrow.apply {
                isVisible = hasPortfolio
                if (hasPortfolio) {
                    setOnClickListener {
                        arrowOnClickAction(listItem.name)
                    }
                }
            }
        }
    }
}