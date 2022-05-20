package com.example.a2022_q2_osovskoy.ui.main_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ViewHolderBannerBinding
import com.example.a2022_q2_osovskoy.databinding.ViewHolderStudentBinding
import com.example.a2022_q2_osovskoy.domain.entity.ListItem

//todo() diffUtils
class ItemsAdapter(
    private val onAcceptClick: (accept: String) -> Unit,
    private val onDeclineClick: (decline: String) -> Unit,
    private val onArrowClickAction: (name: String) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val BANNER_TYPE = 0
        const val STUDENT_TYPE = 1
    }

    var items: List<ListItem> = emptyList()
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                holder.bind(
                    items[position],
                    onAcceptClick = onAcceptClick,
                    onDeclineClick = onDeclineClick
                )
            }
            is StudentViewHolder -> holder.bind(items[position],
                arrowOnClickAction = onArrowClickAction)

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (items[position] is ListItem.BannerItem) {
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
        listItemDto: ListItem,
        onAcceptClick: (accept: String) -> Unit,
        onDeclineClick: (decline: String) -> Unit,
    ) {
        listItemDto as ListItem.BannerItem

        with(viewBinding) {
            textReq.text = listItemDto.description
            newReq.text = listItemDto.title
            setUpListener(this, onAcceptClick, onDeclineClick)
        }
    }


    private fun setUpListener(
        binding: ViewHolderBannerBinding,
        onAcceptClick: (accept: String) -> Unit,
        onDeclineClick: (decline: String) -> Unit,
    ) {
        with(binding) {
            acceptButton.setOnClickListener {
                onAcceptClick(acceptButton.text.toString())
            }
            declineButton.setOnClickListener {
                onDeclineClick(declineButton.text.toString())
            }
        }
    }
}

class StudentViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_student, parent, false)
    ) {
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