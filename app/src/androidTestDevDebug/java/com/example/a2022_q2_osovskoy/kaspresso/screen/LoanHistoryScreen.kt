package com.example.a2022_q2_osovskoy.kaspresso.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R
import org.hamcrest.Matcher

object LoanHistoryScreen : Screen<LoanHistoryScreen>() {

    val logoutButton = KButton { withId(R.id.logoutButton) }
    val applyNewLoanButton = KButton { withId(R.id.applyNewLoanButton) }
    val historyLoanId = KTextView { withId(R.id.historyLoanId) }
    val historyDateAndAmount = KTextView { withId(R.id.historyDateAndAmount) }
    val historyStatus = KTextView { withId(R.id.historyStatus) }

    val loansList = KRecyclerView(
        builder = { withId(R.id.loansRecycler) },
        itemTypeBuilder = { itemType(::LoanItemHolder) }
    )

    class LoanItemHolder(parent: Matcher<View>) : KRecyclerItem<LoanItemHolder>(parent) {
        val loanItemId = KTextView(parent) { withId(R.id.loanItemId) }
        val loanItemStatus = KTextView(parent) { withId(R.id.loanItemStatus) }
        val loanItemDate = KTextView(parent) { withId(R.id.loanItemDate) }
        val loanItemAmount = KTextView(parent) { withId(R.id.loanItemAmount) }
    }
}