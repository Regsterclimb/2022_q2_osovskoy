package com.example.a2022_q2_osovskoy.kaspresso.screen

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.tabs.KTabLayout
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R

object LoanDetailScreen : Screen<LoanDetailScreen>() {

    val loanInfoTable = KTabLayout { withId(R.id.loanInfoTable) }
    val detailAmountRow = KTextView { withId(R.id.detailAmountRow) }
    val detailAmount = KTextView { withId(R.id.detailAmount) }
    val detailDateRow = KTextView { withId(R.id.detailDateRow) }
    val detailDate = KTextView { withId(R.id.detailDate) }
    val detailPercentRow = KTextView { withId(R.id.detailPercentRow) }
    val detailPercent = KTextView { withId(R.id.detailPercent) }
    val detailPeriodRow = KTextView { withId(R.id.detailPeriodRow) }
    val detailPeriod = KTextView { withId(R.id.detailPeriod) }
    val detailIdRow = KTextView { withId(R.id.detailIdRow) }
    val detailId = KTextView { withId(R.id.detailId) }
    val detailStateRow = KTextView { withId(R.id.detailStateRow) }
    val detailState = KTextView { withId(R.id.detailState) }
    val detailsNameRow = KTextView { withId(R.id.detailsNameRow) }
    val detailsName = KTextView { withId(R.id.detailsName) }
    val detailsLastNameRow = KTextView { withId(R.id.detailsLastNameRow) }
    val detailsLastName = KTextView { withId(R.id.detailsLastName) }
    val detailsPhoneRow = KTextView { withId(R.id.detailsPhoneRow) }
    val detailsLastPhone = KTextView { withId(R.id.detailsLastPhone) }

    val approvalContainer = KTabLayout { withId(R.id.approvalContainer) }

    val handImage = KImageView { withId(R.id.detailHandImage) }
    val detailHandText = KTextView { withId(R.id.detailHandText) }

    val detailCardImage = KImageView { withId(R.id.detailCardImage) }
    val detailCardText = KTextView { withId(R.id.detailCardText) }

    val detailPassportImage = KImageView { withId(R.id.detailPassportImage) }
    val detailPassportText = KTextView { withId(R.id.detailPassportText) }

}