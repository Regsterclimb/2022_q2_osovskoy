package com.example.a2022_q2_osovskoy.ui.loanhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanViewHolderBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.extentions.changeColor

class LoansAdapter(private val onLoanClick: (loanId: Long) -> Unit) :
    ListAdapter<Loan, LoanViewHolder>(LoanListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder =
        LoanViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.loan_view_holder, parent, false))

    override fun onBindViewHolder(holderList: LoanViewHolder, position: Int) {
        holderList.bind(getItem(position), onLoanClick)
    }
}

class LoanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding by viewBinding(LoanViewHolderBinding::bind)

    fun bind(loan: Loan, onLoanClick: (loanId: Long) -> Unit) {
        with(loan) {
            with(binding) {
                loanItemAmount.text = Long.toString()
                loanItemDate.text = date
                loanItemId.text = id.toString()
                loanItemStatus.apply {
                    text = state
                    changeColor(state)
                }
            }
            itemView.setOnClickListener {
                onLoanClick(id)
            }
        }
    }
}

class LoanListCallBack : DiffUtil.ItemCallback<Loan>() {

    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem == newItem
    }
}