package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import androidx.navigation.NavOptions
import androidx.security.crypto.MasterKey
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

//todo()
fun String.addPercent() = "$this %"

fun String.addRub() = "$this Rub"

fun String.addDays() = "$this дн."

fun LoanResponse.toLoan(): Loan =
    Loan(id, amount, date = date.substring(0, date.indexOf("T")), state, percent)

fun LoanResponse.toLoanEntity(): LoanEntity =
    LoanEntity(id, date, firstName, lastName, amount, period, phoneNumber, state, percent)

fun LoanEntity.toLoan(): Loan =
    Loan(id, amount, date = date.substring(0, date.indexOf("T")), state, percent)

suspend fun <T> CoroutineDispatcher.execute(block: suspend () -> T) : ResultState<T>  = withContext(this) {
    try {
        ResultState.Success(block.invoke())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        when (e.code()) {
            400 -> ResultState.Error(BadRequestException())
            401 -> ResultState.Error(UnauthorizedException())
            403 -> ResultState.Error(ForbiddenException())
            404 -> ResultState.Error(NotFoundException())
            in 500..599 -> ResultState.Error(ServerIsNotRespondingException())
            else -> ResultState.Error(e)
        }
    } catch (e: Throwable) {
        ResultState.Error(e)
    }
}

suspend fun <T> CoroutineDispatcher.myExecute(block: suspend () -> T) = withContext(this) {
    try {
        ResultState.Success(block.invoke())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        when (e.code()) {
            400 -> throw BadRequestException()
            401 -> throw UnauthorizedException()
            403 -> throw ForbiddenException()
            404 -> throw NotFoundException()
            in 500..599 -> throw ServerIsNotRespondingException()
            else -> ResultState.Error(e)
        }
    } catch (e: Throwable) {
        throw e
    }
}

class BadRequestException : RuntimeException() // такой пользователь уже найден
class UnauthorizedException : RuntimeException() // неавторизованный пользователь
class ForbiddenException : RuntimeException() // означает ограничение или отсутствие доступа к материалу на странице, которую вы пытаетесь загрузить.

class NotFoundException : RuntimeException() // пусто
class ServerIsNotRespondingException : RuntimeException() //сервер не отвечает

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

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun hasInternetConnection(dispatcher: CoroutineDispatcher): Boolean =
    withContext(dispatcher) {
        try {
            val timeoutMs = 1500
            val sock = Socket()
            val socket: SocketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socket, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }
