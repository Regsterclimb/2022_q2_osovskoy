package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.edit.KTextInputLayout
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.tabs.KTabLayout
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R

object LoanRequestScreen : Screen<LoanRequestScreen>() {

    val requestTitle = KTextView { withId(R.id.requestTitle) }
    val requestTab = KTabLayout { withId(R.id.requestTab) }
    val requestAmountRow = KTextView { withId(R.id.requestAmountRow) }
    val requestAmount = KTextView { withId(R.id.requestAmount) }
    val requestPercentRow = KTextView { withId(R.id.requestPercentRow) }
    val requestPercent = KTextView { withId(R.id.requestPercent) }
    val requestPeriodRow = KTextView { withId(R.id.requestPeriodRow) }
    val requestPeriod = KTextView{withId(R.id.requestPeriod)}
    val loanNameInput = KTextInputLayout { withId(R.id.loanNameInput) }
    val loanNameEdit = KEditText { withId(R.id.loanNameEdit) }
    val loanLastNameInput = KTextInputLayout { withId(R.id.loanLastNameInput) }
    val loanLastNameEdit = KEditText { withId(R.id.loanLastNameEdit) }
    val loanPhoneInput = KTextInputLayout { withId(R.id.loanPhoneInput) }
    val loanPhoneEdit = KEditText { withId(R.id.loanPhoneEdit) }

    val requestLoanButton = KButton {withId(R.id.requestLoanButton)}

    val requestErrorText = KTextView{withId(R.id.requestErrorText)}
    val requestProgressBar = KTextView{withId(R.id.requestProgressBar)}

}