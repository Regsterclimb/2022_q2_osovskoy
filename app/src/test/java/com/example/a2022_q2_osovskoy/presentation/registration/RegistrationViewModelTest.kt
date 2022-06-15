package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.BadRequestException
import com.example.a2022_q2_osovskoy.utils.exceptions.ForbiddenException
import com.example.a2022_q2_osovskoy.utils.exceptions.ServerIsNotRespondingException
import com.example.a2022_q2_osovskoy.utils.exceptions.UnauthorizedException
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
class RegistrationViewModelTest {

    lateinit var registerUseCase: RegisterUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var observer: Observer<RegState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        registerUseCase = mock()
        observer = mock()
    }
    //todo()
    @Test
    fun `WHEN tryReg Expect regEvent RegStateLoading before RegStateSuccess `() = runTest {
        val name = "Oлег"
        val password = "1234"
        /*val resultState: ResultState<Unit> = ResultState.Success(Unit)

        Mockito.`when`(registerUseCase(BaseUser(name, password))).thenReturn(resultState)*/
        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Success)
    }

    @Test
    fun `WHEN tryReg Expect regEvent as RegStateInputErrorName`() = runTest {
        val name = ""
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        val actual = viewModel.regState.value

        val expected = RegState.InputError.Name

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regEvent as RegStateInputErrorPassword`() = runTest {
        val name = "Олег"
        val password = ""

        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        val actual = viewModel.regState.value

        val expected = RegState.InputError.Password

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regEvent error BadRequestException`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        Mockito.`when`(registerUseCase(BaseUser(name, password))).thenThrow(BadRequestException())
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Error.BadRequest)
    }
    @Test
    fun `WHEN tryReg Expect regEvent error NotFound`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow()
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Error.NotFound)
    }

    @Test
    fun `WHEN tryReg Expect regEvent error Unauthorized`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(UnauthorizedException())
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Error.Unauthorized)
    }
    @Test
    fun `WHEN tryReg Expect regEvent error Forbidden`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(ForbiddenException())
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Error.Forbidden)
    }
    @Test
    fun `WHEN tryReg Expect regEvent error ServerIsNotResponding`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(ServerIsNotRespondingException())
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
        verify(observer).onChanged(RegState.Error.ServerIsNotResponding)
    }
    /*class BadRequestException : RuntimeException()
    class UnauthorizedException : RuntimeException()
    class ForbiddenException : RuntimeException()
    class NotFoundException : RuntimeException()
    class ServerIsNotRespondingException : RuntimeException()*/
}