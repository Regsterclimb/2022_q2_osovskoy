package com.example.a2022_q2_osovskoy.presentation.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.LoginUseCase
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
class AuthViewModelTest {

    private lateinit var loginUseCase: LoginUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var observer: Observer<AuthState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loginUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN tryAuth Expect authState InputErrorName`() = runTest {
        val name = ""
        val password = "1234"

        whenever(loginUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.tryAuth(name, password)

        val expected = AuthState.InputError.Name

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState InputErrorPassword`() = runTest {
        val name = "Олег"
        val password = ""

        whenever(loginUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.tryAuth(name, password)

        val expected = AuthState.InputError.Password

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState Typing`() = runTest {
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.setTyping()

        val expected = AuthState.Typing

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState Success`() = runTest {
        val name = "Oлег"
        val password = "1234"

        whenever(loginUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.tryAuth(name, password)

        val expected = AuthState.Success

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState BadRequest`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        whenever(loginUseCase(base)).thenThrow(BadRequestException())
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.BadRequest

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState Unauthorized`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        val viewModel = AuthViewModel(loginUseCase)

        whenever(loginUseCase(base)).thenThrow(UnauthorizedException())

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.Unauthorized

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState Forbidden`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        whenever(loginUseCase(base)).thenThrow(ForbiddenException())
        val viewModel = AuthViewModel(loginUseCase)

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.Forbidden

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState NotFoundException`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        val viewModel = AuthViewModel(loginUseCase)

        whenever(loginUseCase(base)).thenThrow(NotFoundException())

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.NotFound

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState ServerIsNotResponding`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        val viewModel = AuthViewModel(loginUseCase)

        whenever(loginUseCase(base)).thenThrow(ServerIsNotRespondingException())

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.ServerIsNotResponding

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState NoInternetConnection`() = runTest {
        val name = "Oлег"
        val password = "1234"
        val base = BaseUser(name, password)

        val viewModel = AuthViewModel(loginUseCase)

        whenever(loginUseCase(base)).thenAnswer { throw IOException() }

        viewModel.tryAuth(name, password)

        val expected = AuthState.Error.NoInternetConnection

        val actual = viewModel.authState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryAuth Expect authState Loading`() = runTest {
        val name = "Oлег"
        val password = "1234"

        whenever(loginUseCase(BaseUser(name, password))).thenReturn(Unit)
        val viewModel = AuthViewModel(loginUseCase)
        viewModel.authState.observeForever(observer)
        viewModel.tryAuth(name, password)

        verify(observer).onChanged(AuthState.Loading)
    }
}