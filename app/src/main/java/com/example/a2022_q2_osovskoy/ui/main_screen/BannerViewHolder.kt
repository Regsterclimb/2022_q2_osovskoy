package com.example.a2022_q2_osovskoy.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ViewHolderBannerBinding
import com.example.a2022_q2_osovskoy.domain.entity.ListItem

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
            setUpListeners(this, onAcceptClick, onDeclineClick)
        }
    }

    private fun setUpListeners(
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
