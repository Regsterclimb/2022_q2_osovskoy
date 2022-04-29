package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R


object CurrencyChangeScreen : Screen<CurrencyChangeScreen>() {

    val currencyName = KTextView { withId(R.id.currencyNameValue) }
    val currencyValue = KTextView { withId(R.id.currencyActualValue) }
    val currencyCharCode = KTextView { withId(R.id.currencyCharCodeValue) }

    val result = KTextView { withId(R.id.result) }
    val editText = KEditText { withId(R.id.currencyEditText) }
    val changeButton = KButton { withId(R.id.currencyChangeButton) }

    val backButton = KButton { withId(R.id.currencyBackButton) }
}