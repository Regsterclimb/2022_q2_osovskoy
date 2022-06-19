package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RegistrationViewModelTest {

    lateinit var registerUseCase: RegisterUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var observer: Observer<RegState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        registerUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN tryReg Expect regState RegStateTyping `() = runTest {
        val viewModel = RegistrationViewModel(registerUseCase)

        viewModel.setTyping()

        val expected = RegState.Typing

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState RegStateSuccess `() = runTest {
        val name = "Oлег"
        val password = "1234"

        whenever(registerUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = RegistrationViewModel(registerUseCase)

        viewModel.tryReg(name, password)

        val expected = RegState.Success

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState RegStateLoading `() = runTest {
        val name = "Oлег"
        val password = "1234"

        whenever(registerUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regState.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegState.Loading)
    }

    @Test
    fun `WHEN tryReg Expect regState as RegStateInputErrorName`() = runTest {
        val name = ""
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)

        viewModel.tryReg(name, password)

        val actual = viewModel.regState.value

        val expected = RegState.InputError.Name

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState as RegStateInputErrorPassword`() = runTest {
        val name = "Олег"
        val password = ""

        val viewModel = RegistrationViewModel(registerUseCase)

        viewModel.tryReg(name, password)

        val actual = viewModel.regState.value

        val expected = RegState.InputError.Password

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error BadRequestException`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(BadRequestException())

        viewModel.tryReg(name, password)

        val expected = RegState.Error.BadRequest

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error NotFound`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(NotFoundException())

        viewModel.tryReg(name, password)

        val expected = RegState.Error.NotFound

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error Unauthorized`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)

        whenever(registerUseCase(BaseUser(name, password))).thenThrow(UnauthorizedException())

        viewModel.tryReg(name, password)

        val expected = RegState.Error.Unauthorized

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error Forbidden`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        whenever(registerUseCase(BaseUser(name, password))).thenThrow(ForbiddenException())

        viewModel.tryReg(name, password)

        val expected = RegState.Error.Forbidden

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error ServerIsNotResponding`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)

        whenever(registerUseCase(BaseUser(name,
            password))).thenThrow(ServerIsNotRespondingException())

        viewModel.tryReg(name, password)

        val expected = RegState.Error.ServerIsNotResponding

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState error NoInternetConnection`() = runTest {
        val name = "OЛЕГ"
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)

        whenever(registerUseCase(BaseUser(name,
            password))).thenAnswer { throw IOException() }

        viewModel.tryReg(name, password)

        val expected = RegState.Error.NoInternetConnection

        val actual = viewModel.regState.value

        assertEquals(expected, actual)
    }
}