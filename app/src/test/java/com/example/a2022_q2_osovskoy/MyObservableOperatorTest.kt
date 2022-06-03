package com.example.a2022_q2_osovskoy

import org.junit.Test

class MyObservableOperatorTest {

    @Test
    fun test_1() {
        val values = (10 downTo 6).toList().toTypedArray()

        MyObservableOperator.Task1.solve()
            .test()
            .assertValues(*values)
    }
}