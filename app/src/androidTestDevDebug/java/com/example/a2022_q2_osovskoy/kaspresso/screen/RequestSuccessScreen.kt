package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R

object RequestSuccessScreen : Screen<RequestSuccessScreen>() {

    val loanSuccessImage = KImageView{withId(R.id.loanSuccessImage)}
    val successTitle = KTextView{withId(R.id.successTitle)}
    val successSecondary = KTextView{withId(R.id.successSecondary)}
    val navToHistoryButton = KButton{withId(R.id.nav_to_history_button)}
    val navToDetailButton = KButton{withId(R.id.nav_to_details_button)}

}