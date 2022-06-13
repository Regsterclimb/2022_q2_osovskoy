package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
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

@ExperimentalCoroutinesApi
class RegistrationViewModelTest {

    lateinit var registerUseCase: RegisterUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var observer: Observer<RegEvent>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        registerUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN tryReg Expect regState RegStateLoading before RegStateSuccess `() = runTest {
        val name = "Oлег"
        val password = "1234"
        val resultState: ResultState<Unit> = ResultState.Success(Unit)

        Mockito.`when`(registerUseCase(BaseUser(name, password))).thenReturn(resultState)
        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regEvent.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegEvent.Loading)
        verify(observer).onChanged(RegEvent.Success)
    }

    @Test
    fun `WHEN tryReg Expect regState as RegStateLoading before RegStateError `() = runTest {
        val name = "OЛЕГ"
        val password = "1234"
        val resultState: ResultState<Unit> = ResultState.Error()

        val viewModel = RegistrationViewModel(registerUseCase)
        Mockito.`when`(registerUseCase(BaseUser(name, password))).thenReturn(resultState)
        viewModel.regEvent.observeForever(observer)

        viewModel.tryReg(name, password)

        verify(observer).onChanged(RegEvent.Loading)
        verify(observer).onChanged(RegEvent.Error)
    }

    @Test
    fun `WHEN tryReg Expect regState as RegStateInputErrorName`() = runTest {
        val name = ""
        val password = "1234"

        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regEvent.observeForever(observer)

        viewModel.tryReg(name, password)

        val actual = viewModel.regEvent.value

        val expected = RegEvent.InputError.Name

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN tryReg Expect regState as RegStateInputErrorPassword`() = runTest {
        val name = "Олег"
        val password = ""

        val viewModel = RegistrationViewModel(registerUseCase)
        viewModel.regEvent.observeForever(observer)

        viewModel.tryReg(name, password)

        val actual = viewModel.regEvent.value

        val expected = RegEvent.InputError.Password

        assertEquals(expected, actual)
    }
}