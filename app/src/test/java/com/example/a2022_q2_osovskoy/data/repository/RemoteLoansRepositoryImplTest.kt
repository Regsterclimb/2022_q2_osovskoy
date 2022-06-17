package com.example.a2022_q2_osovskoy.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.local.LoansLocalDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import kotlinx.coroutines.CoroutineDispatcher
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
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RemoteLoansRepositoryImplTest {

    private lateinit var loansDataSource: LoansDataSource
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var loansLocalDataSource: LoansLocalDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loansDataSource = mock()
        dispatcher = Dispatchers.Unconfined
        loansLocalDataSource = mock()
    }

    @Test
    fun `WHEN requestLoan Expect correct Result`() = runTest {
        val request = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "+1238493"
        )
        val loan = LoanResponse(
            "25.12.2021T1234",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            145,
            "APPROVED",
            8.5)

        whenever(loansDataSource.requestLoan(request)).thenReturn(loan)
        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        val expected = Loan(145, 15000.0, "25.12.2021", "APPROVED", 8.5)

        val actual = remoteRep.requestLoan(LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "+1238493"
        ))
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLoanCondition Expect correct result`() = runTest {
        val loanCondition = LoanConditionResponse(45, 15000, 8.5)

        whenever(loansDataSource.getLoanCondition()).thenReturn(loanCondition)

        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        val expected = LoanCondition(45, 15000, 8.5)

        val actual = remoteRep.getLoanCondition()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getAll Expect correct result`() = runTest {
        val loanResponse = listOf(
            LoanResponse(
                "25.12.2021T1234",
                "Олег",
                "Олегович",
                15000.0,
                45,
                "+1238493",
                145,
                "APPROVED",
                8.5))

        whenever(loansDataSource.getAll()).thenReturn(loanResponse)

        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        val expected = listOf(
            Loan(
                145,
                15000.0,
                "25.12.2021",
                "APPROVED",
                8.5)
        )

        val actual = remoteRep.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getAll Expect correct input to loansLocalDataSource`() = runTest {
        val loanResponse = listOf(
            LoanResponse(
                "25.12.2021T1234",
                "Олег",
                "Олегович",
                15000.0,
                45,
                "+1238493",
                145,
                "APPROVED",
                8.5))

        whenever(loansDataSource.getAll()).thenReturn(loanResponse)

        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        remoteRep.getAll()

        val expected = listOf(
            LoanEntity(145,
                "25.12.2021",
                "Олег",
                "Олегович",
                15000.0,
                45,
                "+1238493",
                "APPROVED",
                8.5)
        )

        verify(loansLocalDataSource, Mockito.times(1)).insertAll(expected)
    }

    @Test
    fun `WHEN getById Expect correct Result`() = runTest {
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

        whenever(loansDataSource.getLoanById(145)).thenReturn(loanResponse)

        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        val expected = LoanDetail("Олег",
            145, "25.12.2021",
            15000.0,
            "APPROVED",
            8.5,
            "+1238493",
            "Олегович",
            45
        )

        val actual = remoteRep.getLoanById(145)

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getById Expect input to loansLocalDataSource`() = runTest {
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

        whenever(loansDataSource.getLoanById(145)).thenReturn(loanResponse)

        val remoteRep = RemoteLoansRepositoryImpl(loansDataSource, dispatcher, loansLocalDataSource)

        remoteRep.getLoanById(145)

        val expected = LoanEntity(145,
            "25.12.2021",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            "APPROVED",
            8.5)

        verify(loansLocalDataSource,Mockito.times(1)).insertLoan(expected)
    }
}