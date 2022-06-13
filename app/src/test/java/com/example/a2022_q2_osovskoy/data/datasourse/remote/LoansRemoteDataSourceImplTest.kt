package com.example.a2022_q2_osovskoy.data.datasourse.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.network.LoansApi
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoansRemoteDataSourceImplTest {

    lateinit var loansApi: LoansApi

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loansApi = mock()
    }

    @Test
    fun `WHEN getAll Expect correct Result`() = runTest {
        val loanResponse = LoanResponse(
            "24",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5)

        whenever(loansApi.getAll()).thenReturn(listOf(loanResponse))

        val expected = listOf(LoanResponse(
            "24",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5))

        val actual = loansApi.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLoanCondition Expect correct Result`() = runTest {
        val conditionResponse = LoanConditionResponse(45, 15000, 4.5)

        whenever(loansApi.getLoanCondition()).thenReturn(conditionResponse)

        val expected = LoanConditionResponse(45, 15000, 4.5)

        val actual = loansApi.getLoanCondition()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN requestLoan Expect correct Result`() = runTest {
        val conditionResponse = LoanConditionResponse(45, 15000, 4.5)

        whenever(loansApi.getLoanCondition()).thenReturn(conditionResponse)

        val expected = LoanConditionResponse(45, 15000, 4.5)

        val actual = loansApi.getLoanCondition()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLoanById Expect correct Result`() = runTest {
        val loanId = 45L
        val loanResponse = LoanResponse("24", "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            45,
            "APPROVED",
            8.5)

        whenever(loansApi.getLoanById(loanId)).thenReturn(loanResponse)

        val expected = LoanResponse("24", "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            45,
            "APPROVED",
            8.5)

        val actual = loansApi.getLoanById(loanId)

        assertEquals(expected, actual)
    }
    @Test
    fun `WHEN loanRequest Expect correct Result`() = runTest {
        val loanId = 45L
        val loanRequest = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "+1238493")
        val loanResponse = LoanResponse("24", "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            45,
            "APPROVED",
            8.5)

        whenever(loansApi.request(loanRequest)).thenReturn(loanResponse)

        val expected = LoanResponse("24", "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            45,
            "APPROVED",
            8.5)

        val actual = loansApi.request(loanRequest)

        assertEquals(expected, actual)
    }

}