package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import androidx.navigation.NavOptions
import androidx.security.crypto.MasterKey
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

//todo()
fun String.addPercent() = "$this %"

fun String.addRub() = "$this Rub"

fun String.addDays() = "$this дн."

fun LoanResponse.toLoan(): Loan =
    Loan(id, amount, date = date.substring(0, date.indexOf("T")), state, percent)

suspend fun <T> CoroutineDispatcher.execute(block: suspend () -> T) = withContext(this) {
    try {
        ResultState.Success(block.invoke())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        ResultState.Error()
    }
}

fun provideMasterKey(context: Context) =
    MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

fun LoanConditionResponse.toLoanCondition(): LoanCondition =
    LoanCondition(period, maxAmount, percent)

fun provideFlatNavOptionsBuilder(isSingleTop: Boolean): NavOptions.Builder =
    NavOptions.Builder().setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .setLaunchSingleTop(isSingleTop)
