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
            mainAmount.apply {
                text = loanCondition.maxAmount.toString()
                append(itemView.context.getString(R.string.addRub))
            }
            mainPercent.apply {
                text = loanCondition.percent.toString()
                append(itemView.context.getString(R.string.addPercent))
            }
            mainPeriod.apply {
                text = loanCondition.period.toString()
                append(itemView.context.getString(R.string.addDays))
            }
            openRequestButton.setOnClickListener {
                onRequestClick(loanCondition)
            }
        }
    }
}

class LoanConditionListCallBack : DiffUtil.ItemCallback<LoanCondition>() {

    override fun areItemsTheSame(oldItem: LoanCondition, newItem: LoanCondition): Boolean =
        oldItem.maxAmount == newItem.maxAmount

    override fun areContentsTheSame(oldItem: LoanCondition, newItem: LoanCondition): Boolean =
        oldItem == newItem
}