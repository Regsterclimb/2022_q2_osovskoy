package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.edit.KTextInputLayout
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.scroll.KScrollView
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R

object AuthScreen : Screen<AuthScreen>() {

    val authTitle = KTextView { withId(R.id.authTitle) }
    val authSignUp = KTextView { withId(R.id.authSignUpText) }
    val authScroll = KScrollView{withId(R.id.authScroll)}

    val authNameInput = KTextInputLayout { withId(R.id.authNameInput) }
    val authNameEdit = KEditText { withId(R.id.authNameEdit) }
    val authPasswordInput = KTextInputLayout { withId(R.id.authPasswordInput) }
    val authPasswordEdit = KEditText { withId(R.id.authPasswordEdit) }


    val authButton = KButton { withId(R.id.authButton) }
    val regText = KTextView { withId(R.id.registrationText) }

}