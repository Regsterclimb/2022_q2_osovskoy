package com.example.a2022_q2_osovskoy.kaspresso.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.swiperefresh.KSwipeRefreshLayout
import com.agoda.kakao.tabs.KTabLayout
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R
import org.hamcrest.Matcher

object LoanConditionScreen : Screen<LoanConditionScreen>() {

    val openHistory = KButton { withId(R.id.openHistory) }
    val showInstructionButton = KButton { withId(R.id.showInstructionButton) }
    val instructionTab = KTabLayout { withId(R.id.instructionValue) }

    val conditionTitle = KTextView { withId(R.id.conditionTitle) }

    val swipeRefresh = KSwipeRefreshLayout {withId(R.id.loanConditionSwipeRefresh)}

    val conditionList = KRecyclerView(
        builder = { withId(R.id.loanConditionRecycler) },
        itemTypeBuilder = { itemType(::LoanConditionHolder) }
    )

    class LoanConditionHolder(parent: Matcher<View>) : KRecyclerItem<LoanConditionHolder>(parent) {

        val bannerContainer = KImageView(parent) { withId(R.id.bannerContainer) }
        val mainImage = KImageView(parent) { withId(R.id.mainImage) }

        val requestAmountRow = KTextView(parent) { withId(R.id.requestAmountRow) }
        val requestPercentRow = KTextView(parent) { withId(R.id.requestPercentRow) }
        val requestPeriodRow = KTextView(parent) { withId(R.id.requestPeriodRow) }

        val mainAmount = KTextView(parent) { withId(R.id.mainAmount) }
        val mainPercent = KTextView(parent) { withId(R.id.mainPercent) }
        val mainPeriod = KTextView(parent) { withId(R.id.mainPeriod) }

        val requestButton = KButton(parent) { withId(R.id.openRequestButton) }
    }
}