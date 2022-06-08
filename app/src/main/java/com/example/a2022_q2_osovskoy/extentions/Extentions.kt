package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import androidx.security.crypto.MasterKey
import com.example.a2022_q2_osovskoy.data.model.network.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.model.network.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

fun LoanResponse.toLoan(): Loan =
    Loan(id, amount, date = date.substring(0, date.indexOf("T")), state, percent)

fun provideMasterKey(context: Context) =
    MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

fun LoanConditionResponse.toLoanCondition(): LoanCondition =
    LoanCondition(period, maxAmount, percent)