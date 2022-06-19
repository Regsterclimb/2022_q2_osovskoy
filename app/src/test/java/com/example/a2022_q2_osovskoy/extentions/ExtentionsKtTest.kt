package com.example.a2022_q2_osovskoy.extentions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class ExtensionsTest {

    private lateinit var dispatcher: CoroutineDispatcher

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        dispatcher = UnconfinedTestDispatcher()
    }

    @Test
    fun `When Response toLoan Expect Correct Result`() {
        val loanResponse = LoanResponse(
            "25.12.2021T1234",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5)

        val actual = loanResponse.toLoan()
        val expected = Loan(
            145,
            15000.0,
            "25.12.2021",
            "APPROVED",
            8.5)
        assertEquals(expected, actual)
    }

    @Test
    fun `When Response toLoanEntity Expect Correct Result`() {
        val response = LoanResponse(
            "25.12.2021T1234",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5)

        val expected = LoanEntity(145,
            "25.12.2021",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            "APPROVED",
            8.5)
        val actual = response.toLoanEntity()

        assertEquals(expected, actual)

    }

    @Test
    fun `When Response toLoanDetails Expect Correct Result`() {
        val response = LoanResponse(
            "25.12.2021T1234",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5)

        val expected = LoanDetail("Олег",
            145, "25.12.2021",
            15000.0,
            "APPROVED",
            8.5,
            "+1238493",
            "Олегович",
            45
        )

        val actual = response.toLoanDetails()

        assertEquals(expected, actual)
    }

    @Test
    fun `When Entity toLoan Expect Correct Result`() {
        val loanEntity = LoanEntity(
            145,
            "24.5.1992",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234513",
            "APPROVED",
            8.5
        )
        val actual = loanEntity.toLoan()

        val expected = Loan(
            145,
            15000.0,
            "24.5.1992",
            "APPROVED",
            8.5)
        assertEquals(expected, actual)
    }

    @Test
    fun `When Entity toLoanDetails Expect Correct Result`() {
        val loanEntity = LoanEntity(
            135,
            "24.5.1992",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234513",
            "APPROVED",
            8.5
        )

        val expected = LoanDetail(
            "Олег",
            135,
            "24.5.1992",
            15000.0,
            "APPROVED",
            8.5,
            "1234513",
            "Олегович",
            45
        )
        val actual = loanEntity.toLoanDetails()

        assertEquals(expected, actual)

    }

    @Test
    fun `WHen execute Expect Success`() = runTest {
        val test: suspend () -> Unit = { LoanCondition(15, 15, 1.5) }

        val actual = dispatcher.execute { test }.invoke()

        val expected = Unit

        assertEquals(expected, actual)
    }

    @Test(expected = IOException::class)
    fun `WHen execute Expect IoException`() = runTest {
        val test: suspend () -> Unit = { throw IOException() }

        dispatcher.execute { test }.invoke()
    }

    @Test
    fun `When toLoanCondition Expect correct result`() {
        val loanConditionResponse = LoanConditionResponse(45, 15000, 8.5)

        val expected = LoanCondition(45, 15000, 8.5)

        val actual = loanConditionResponse.toLoanCondition()

        assertEquals(expected, actual)
    }

    @Test(expected = HttpException::class)
    fun `WHen execute Expect HttpException`() = runTest {

        val test: suspend () -> Unit = {
            throw HttpException(Response.error<ResponseBody>(400,
                "some content".toResponseBody("plain/text".toMediaTypeOrNull())))
        }
        dispatcher.execute { test }.invoke()
    }
}