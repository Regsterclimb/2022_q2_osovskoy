package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import androidx.navigation.NavOptions
import androidx.security.crypto.MasterKey
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

fun LoanResponse.toLoan(): Loan = Loan(
    id,
    amount,
    date = date.substring(0, date.indexOf("T")),
    state,
    percent
)

fun LoanResponse.toLoanEntity(): LoanEntity = LoanEntity(
    id,
    date = date.substring(0, date.indexOf("T")),
    firstName,
    lastName,
    amount,
    period,
    phoneNumber,
    state,
    percent
)

fun LoanResponse.toLoanDetails(): LoanDetail = LoanDetail(
    firstName,
    id,
    date = date.substring(0, date.indexOf("T")),
    amount,
    state,
    percent,
    phoneNumber,
    lastName,
    period
)

fun LoanEntity.toLoan(): Loan = Loan(
    id,
    amount,
    date,
    state,
    percent
)

fun LoanEntity.toLoanDetails(): LoanDetail = LoanDetail(
    firstName,
    id,
    date,
    amount,
    state,
    percent,
    phoneNumber,
    lastName,
    period
)

suspend fun <T> CoroutineDispatcher.execute(block: suspend () -> T) = withContext(this) {
    try {
        block.invoke()
    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        throw e
    } catch (e: HttpException) {
        when (e.code()) {
            400 -> throw BadRequestException()
            401 -> throw UnauthorizedException()
            403 -> throw ForbiddenException()
            404 -> throw NotFoundException()
            in 500..599 -> throw ServerIsNotRespondingException()
            else -> throw e
        }
    } catch (e: Throwable) {
        throw e
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

fun String.addPercent() = "$this %"

fun String.addRub() = "$this Rub"

fun String.addDays() = "$this дн."


