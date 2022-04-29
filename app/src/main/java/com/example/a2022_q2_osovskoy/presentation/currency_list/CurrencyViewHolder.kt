package com.example.a2022_q2_osovskoy.presentation.currency_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.databinding.CurrencyViewHolderBinding
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(CurrencyViewHolderBinding::bind)

    fun onBind(
        currency: MyCurrency,
        onItemListener: (myCurrency: MyCurrency) -> Unit,
    ) {
        with(viewBinding) {
            holderCurrencyName.text = String.format(currency.name + " " + currency.charCode) //Тоже выносить в domain(модельку)? как и toString?
            holderValue.text = currency.value.toString()
            holderNominal.text = currency.nominal.toString()
            holderPrevious.text = currency.previous.toString()
        }
        itemView.setOnClickListener {
            onItemListener(currency)
        }
    }
}