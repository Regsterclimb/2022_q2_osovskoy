package com.example.a2022_q2_osovskoy.kaspresso.tools.annotation

@Repeatable
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class TestCase(val name: String, val description: String = "")
