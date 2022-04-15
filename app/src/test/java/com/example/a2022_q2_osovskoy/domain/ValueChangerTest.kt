package com.example.a2022_q2_osovskoy.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ValueChangerTest.ValueRefactorTest::class,
    ValueChangerTest.DoubleRoundTest::class
)
class ValueChangerTest {

    @RunWith(Parameterized::class)
    class ValueRefactorTest(
        private val value: Double,
        private val nominal: Int,
        private val expected: Double
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = listOf(
                arrayOf(0, 1, 0),
                arrayOf(24.555, 1, 24.555),
                arrayOf(1000.1234567, 10, 100.01234567),
                arrayOf(1000.792392392, 100, 10.00792392392),
                arrayOf(1000, 1000, 1),
                arrayOf(1000, 10000, 0.1),
                arrayOf(-1000, 10000, -0.1),
                arrayOf(-1000.2332, 10000, -0.10002332),
            )
        }

        private val valueChanger: ValueChanger = ValueChanger.Base()
        private val delta = 0.0

        @Test
        fun `WHEN refactorValue EXPECT correct result`() {
            val actual = valueChanger.refactorValue(value, nominal)

            assertEquals(expected, actual, delta)
        }

        @Test(expected = RuntimeException::class)
        fun `WHEN refactorValue by wrong nominal EXPECT RuntimeException`() {

            valueChanger.refactorValue(1000.15, 100000)
        }
    }

    @RunWith(Parameterized::class)
    class DoubleRoundTest(
        private val value: Double,
        private val expected: Double
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = listOf(
                arrayOf(0, 0),
                arrayOf(24.555, 24.555),
                arrayOf(1000.1234567, 1000.1235),
                arrayOf(1234.792392392, 1234.7924),
                arrayOf(-50.233223241, -50.2332),
            )
        }

        private val valueChanger: ValueChanger = ValueChanger.Base()
        private val delta = 0.0

        @Test
        fun `WHEN roundDouble EXPECT correct result`() {

            val actual = valueChanger.roundValue(value)

            assertEquals(expected, actual, delta)
        }
    }
}