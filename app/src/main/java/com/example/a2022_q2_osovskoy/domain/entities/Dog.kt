package com.example.a2022_q2_osovskoy.domain.entities

data class Dog(
    override val name: String,
    override val age: String,
) : Animal {

    override fun voice(): String =
        "I'm a dog. My name is $name, I'm $age."
}
