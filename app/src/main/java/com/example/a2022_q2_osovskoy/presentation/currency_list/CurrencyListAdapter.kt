package com.example.a2022_q2_osovskoy.presentation.currency_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency

class CurrencyListAdapter(
    private val onCurrencyClick: (myCurrency: MyCurrency) -> Unit,
) : ListAdapter<MyCurrency, CurrencyViewHolder>(CurrencyListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_view_holder, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(getItem(position), onCurrencyClick)
    }
}

class CurrencyListCallBack : DiffUtil.ItemCallback<MyCurrency>() {

    override fun areItemsTheSame(oldItem: MyCurrency, newItem: MyCurrency): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyCurrency, newItem: MyCurrency): Boolean {
        return oldItem == newItem
    }
}
