package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanConditionHolderBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.extentions.addDays
import com.example.a2022_q2_osovskoy.extentions.addPercent
import com.example.a2022_q2_osovskoy.extentions.addRub

class LoanConditionAdapter(private val onLoanClick: (loanCondition: LoanCondition) -> Unit) :
    ListAdapter<LoanCondition, LoanConditionViewHolder>(
        LoanConditionListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanConditionViewHolder =
        LoanConditionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.loan_condition_holder, parent, false))

    override fun onBindViewHolder(holderListCondition: LoanConditionViewHolder, position: Int) {
        holderListCondition.bind(getItem(position), onLoanClick)
    }
}

class LoanConditionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding by viewBinding(LoanConditionHolderBinding::bind)

    fun bind(loanCondition: LoanCondition, onRequestClick: (loanCondition: LoanCondition) -> Unit) {
        with(binding) {
            mainAmount.text = loanCondition.maxAmount.toString().addRub()
            mainPercent.text = loanCondition.percent.toString().addPercent()
            mainPeriod.text = loanCondition.period.toString().addDays()
            openRequestButton.setOnClickListener {
                onRequestClick(loanCondition)
            }
        }
    }
}

class LoanConditionListCallBack : DiffUtil.ItemCallback<LoanCondition>() {

    override fun areItemsTheSame(oldItem: LoanCondition, newItem: LoanCondition): Boolean {
        return oldItem.maxAmount == newItem.maxAmount
    }

    override fun areContentsTheSame(oldItem: LoanCondition, newItem: LoanCondition): Boolean {
        return oldItem == newItem
    }
}