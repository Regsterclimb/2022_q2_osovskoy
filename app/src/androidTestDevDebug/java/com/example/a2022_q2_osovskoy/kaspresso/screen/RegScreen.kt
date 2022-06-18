package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.edit.KTextInputLayout
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R

object RegScreen : Screen<RegScreen>() {

    val regTitle = KTextView { withId(R.id.regTitle) }
    val regSignUp = KTextView { withId(R.id.regUp) }

    val regNameInput = KTextInputLayout { withId(R.id.regNameInput) }
    val regNameEdit = KEditText { withId(R.id.regNameEdit) }
    val regPasswordInput = KTextInputLayout { withId(R.id.regPasswordInput) }
    val regPasswordEdit= KEditText { withId(R.id.regPasswordEdit) }

    val regButton = KButton { withId(R.id.regButton) }
    val authText = KTextView { withId(R.id.authText) }
}