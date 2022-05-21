package com.example.a2022_q2_osovskoy.ui.main_screen

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2022_q2_osovskoy.domain.entity.ListItem


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
            is BannerViewHolder -> holder.bind(items[position], onAcceptClick, onDeclineClick)

            is StudentViewHolder -> holder.bind(items[position], onArrowClickAction)

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int = if (items[position] is ListItem.BannerItem) {
        BANNER_TYPE
    } else {
        STUDENT_TYPE
    }
}